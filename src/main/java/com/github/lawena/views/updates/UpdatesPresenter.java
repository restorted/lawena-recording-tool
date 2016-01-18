package com.github.lawena.views.updates;

import com.github.lawena.Messages;
import com.github.lawena.config.LawenaProperties;
import com.github.lawena.domain.Build;
import com.github.lawena.domain.UpdateResult;
import com.github.lawena.event.NewVersionAvailable;
import com.github.lawena.event.NewVersionDismissed;
import com.github.lawena.repository.ImageRepository;
import com.github.lawena.service.TaskService;
import com.github.lawena.service.VersionService;
import com.github.lawena.task.DownloadTask;
import com.github.lawena.task.UpdateSetupTask;
import com.github.lawena.task.UpdatesChecker;
import com.github.lawena.task.WebViewLoader;
import com.threerings.getdown.data.Resource;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.github.lawena.util.LwrtUtils.interceptAnchors;

@Component
public class UpdatesPresenter {

    private static final Logger log = LoggerFactory.getLogger(UpdatesPresenter.class);
    private static final String HOME_URL = "https://raw.githubusercontent.com/wiki/iabarca/lawena-recording-tool/Home.md";
    private static final String LOG_URL = "https://raw.githubusercontent.com/wiki/iabarca/lawena-recording-tool/Changelog.md";

    @Autowired
    private VersionService versionService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HostServices hostServices;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private LawenaProperties lawenaProperties;
    @Autowired
    private ApplicationEventPublisher publisher;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab infoTab;
    @FXML
    private WebView infoWebView;
    @FXML
    private WebView changesWebView;

    private NotificationPane resultPane;
    private Action check;
    private Action restart;

    @FXML
    private void initialize() {
        // setup updates sliding notification pane
        resultPane = new NotificationPane(infoWebView);
        infoTab.setContent(resultPane);

        // intercept <a> tags to open links externally
        infoWebView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                interceptAnchors(infoWebView.getEngine().getDocument(), href -> hostServices.showDocument(href));
            }
        });
        changesWebView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                interceptAnchors(changesWebView.getEngine().getDocument(), href -> hostServices.showDocument(href));
            }
        });
        WebViewLoader.LoadSpec loadSpec = new WebViewLoader.LoadSpec();
        Map<WebView, String> map = new LinkedHashMap<>();
        map.put(infoWebView, HOME_URL);
        map.put(changesWebView, LOG_URL);
        loadSpec.getViewUrlMap().putAll(map);
        loadSpec.getStylesheets().add(getClass().getResource("../markdownpad-github.css"));
        taskService.submitTask(new WebViewLoader(loadSpec));

        if (lawenaProperties.getLastSkippedVersion() >= 0) {
            checkForUpdates();
        }
        resultPane.setOnHiding(e -> publisher.publishEvent(new NewVersionDismissed(UpdatesPresenter.this)));
    }

    public void checkForUpdates() {
        UpdatesChecker checker = new UpdatesChecker(versionService);
        taskService.submitTask(checker);
        CompletableFuture.supplyAsync(() -> {
            UpdateResult result;
            try {
                result = checker.get();
            } catch (InterruptedException | ExecutionException e) {
                result = UpdateResult.notFound(e.toString());
            }
            return result;
        }).thenAcceptAsync(this::processUpdateResult, Platform::runLater);
    }

    public void clearCache() {
        versionService.clear();
    }

    private void processUpdateResult(UpdateResult result) {
        log.info("{}", result.toString());
        if (result.getStatus() == UpdateResult.Status.UPDATE_AVAILABLE) {
            Build target = result.getDetails();
            String appbase = versionService.getAppbase();
            long targetVersion = target.getTimestamp();
            if (lawenaProperties.getLastSkippedVersion() == targetVersion) {
                log.info("Skipping this version: {}", targetVersion);
                return;
            }
            URL url;
            try {
                url = new URL(appbase.replace("%VERSION%", "" + targetVersion));
            } catch (MalformedURLException e) {
                log.error("Bad url format: {}", e.toString());
                return;
            }

            check = new Action(Messages.getString("ui.updates.retry"),
                    event -> performUpdate(url, targetVersion)
            );
            restart = new Action(Messages.getString("ui.updates.restart"), event -> {
                Stage stage = (Stage) tabPane.getScene().getWindow();
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
                taskService.scheduleOnShutdown(() -> versionService.upgradeApplication(target));
            });
            Action updateNow = new Action(Messages.getString("ui.updates.updateNow"),
                    event -> performUpdate(url, targetVersion)
            );
            Action skip = new Action("Skip Version", event -> {
                lawenaProperties.setLastSkippedVersion(targetVersion);
                resultPane.hide();
            });

            // First
            resultPane.setText(result.getMessage());
            resultPane.setGraphic(new ImageView(imageRepository.image("/com/github/lawena/fugue/exclamation-24.png")));
            resultPane.getActions().addAll(updateNow, skip);
            resultPane.show();
            publisher.publishEvent(new NewVersionAvailable(this));
        }
    }

    private void performUpdate(URL url, long targetVersion) {
        // Start the background update process
        resultPane.getActions().clear();
        UpdateSetupTask setupTask = new UpdateSetupTask(versionService.getGetdown(), url, targetVersion);
        ProgressIndicator indicator = new ProgressIndicator(-1);
        indicator.setPrefSize(24, 24);
        indicator.setMaxSize(24, 24);
        taskService.submitTask(setupTask);
        resultPane.textProperty().bind(setupTask.messageProperty());
        resultPane.setGraphic(indicator);
        indicator.progressProperty().bind(setupTask.progressProperty());
        CompletableFuture.supplyAsync(() -> {
            try {
                return setupTask.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(Messages.getString("ui.updates.setupInterrupted"), e);
            }
        }).thenApplyAsync(list -> {
            indicator.progressProperty().unbind();
            resultPane.textProperty().unbind();
            DownloadTask downloadTask = new DownloadTask(list);
            taskService.submitTask(downloadTask);
            resultPane.textProperty().bind(downloadTask.messageProperty());
            indicator.progressProperty().bind(downloadTask.progressProperty());
            return downloadTask;
        }, Platform::runLater).thenApplyAsync(task -> {
            try {
                task.get();
                return task;
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(Messages.getString("ui.updates.downloadInterrupted"), e);
            }
        }).thenAcceptAsync(task -> {
            try {
                List<Resource> list = task.get(); // can be null if task failed due to I/O
                if (list != null && !list.isEmpty()) {
                    resultPane.textProperty().unbind();
                    resultPane.setText(Messages.getString("ui.updates.updateReady"));
                    resultPane.setGraphic(new ImageView(imageRepository.image("/com/github/lawena/fugue/exclamation-24.png")));
                    resultPane.getActions().add(restart);
                } else {
                    log.warn("Task failed with exception", task.getException());
                    resultPane.textProperty().unbind();
                    resultPane.setText(Messages.getString("ui.updates.updateFailed"));
                    resultPane.setGraphic(new ImageView(imageRepository.image("/com/github/lawena/fugue/exclamation-24.png")));
                    resultPane.getActions().add(check);
                }
            } catch (InterruptedException | ExecutionException e) {
                // we should not get here normally
                // exceptions on task cancel/interrupt are handled by exceptionally()
                throw new RuntimeException(e);
            }
        }, Platform::runLater).exceptionally(t -> {
            log.info("Update aborted with exception", t);
            resultPane.textProperty().unbind();
            resultPane.setText(Messages.getString("ui.updates.updateAborted", t.getMessage()));
            resultPane.setGraphic(new ImageView(imageRepository.image("/com/github/lawena/fugue/exclamation-24.png")));
            resultPane.getActions().add(check);
            return null;
        });
    }
}
