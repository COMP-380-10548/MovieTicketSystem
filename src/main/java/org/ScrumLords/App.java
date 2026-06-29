package org.ScrumLords;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
       SceneManager.init(stage);
       SceneManager.switchToScene("Login.fxml", null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
