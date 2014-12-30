package com.github.lawena.app;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import joptsimple.OptionSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.github.lawena.app.model.MainModel;
import com.github.lawena.profile.Options;
import com.github.lawena.util.LoggingAppender;

/**
 * Entry point of the application. Invokes main presenter {@linkplain Lawena}
 * 
 * @author Ivan
 *
 */
public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    try {
      SLF4JBridgeHandler.removeHandlersForRootLogger();
      SLF4JBridgeHandler.install();

      ch.qos.logback.classic.Logger rootLog =
          (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("root");
      LoggingAppender appender = new LoggingAppender(rootLog.getLoggerContext());
      rootLog.addAppender(appender);

      final OptionSet optionSet = Options.getParser().parse(args);

      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

        private boolean shown = false;

        @Override
        public void uncaughtException(Thread t, final Throwable e) {
          log.error("Unexpected problem in " + t, e);
          if (!shown) {
            showDialog(e, "[" + t.toString() + "] An error occurred while running Lawena.",
                "Uncaught Exception");
            shown = true;
          }
        }
      });

      // TODO: make this selector standalone or allow bypassing it via args
      log.debug("Parsed options from command line: {}", optionSet.asMap());
      File defaultProfilesFile = null;
      int o =
          JOptionPane.showOptionDialog(null, "Select game to run", "Lawena Recording Tool",
              JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {
                  "Team Fortress 2", "Counter-Strike: Global Offensive"}, "Team Fortress 2");
      if (o == 0) {
        defaultProfilesFile = new File("lwrt/tf/profiles.json");
      } else if (o == 1) {
        defaultProfilesFile = new File("lwrt/csgo/profiles.json");
      } else {
        System.exit(0);
      }

      final Lawena lawena = new Lawena(new MainModel(defaultProfilesFile));
      lawena.setAppender(appender);

      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          try {
            lawena.start();
          } catch (Exception e) {
            log.warn("Problem while launching the GUI", e);
            showDialog(e, "An error occurred while displaying the GUI.", "GUI Launch Failed");
          }
        }
      });
    } catch (Exception e) {
      log.error("Exception while initializing Lawena", e);
      showDialog(e, "An error occurred while starting Lawena.", "Startup Failed");
    }
  }

  private static void showDialog(Throwable t, String title, String header) {
    String msg = t.toString();
    msg = msg.substring(0, Math.min(80, msg.length()));
    JOptionPane.showMessageDialog(null, header + "\nCaused by " + msg
        + "\nLogfile for details. Please report this issue.", title, JOptionPane.ERROR_MESSAGE);
  }
}
