package org.ScrumLords;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of the MovieTicketSystem
 * Initializes the primary stage and launches the primary scene.
 *
 * @author C. Wichman
 * @author A. Garcia
 * @version 1.0
 * @since 2026-06-21
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
       SceneManager.init(stage);
       SceneManager.switchToScene("MainPage", null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
