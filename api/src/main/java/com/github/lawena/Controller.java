package com.github.lawena;

import com.github.lawena.exts.FileProvider;
import com.github.lawena.exts.TagProvider;
import com.github.lawena.exts.ViewProvider;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A JavaFX-based Controller. Allows plugins to interact with this layer of the application.
 *
 * @author Ivan
 */
public interface Controller {

    Model getModel();

    void setModel(Model model);

    Stage getStage();

    void setStage(Stage stage);

    NodeGroups getNodeGroups();

    Pane getLogPane();

    List<TagProvider> getTagProviders();

    ObservableList<Task<?>> getTasks();

    Button getLaunchButton();

    List<ViewProvider> getViewProviders();

    List<FileProvider> getFileProviders();

    void disable(boolean value);

    void saveProfiles();

}