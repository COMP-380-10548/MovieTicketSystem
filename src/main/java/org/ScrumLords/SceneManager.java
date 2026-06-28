package org.ScrumLords;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class SceneManager {

    private static Stage currStage;

    public static void init(Stage stage) {
        currStage = stage;
    }

    public static <T> void switchToScene(String fxmlPath, Consumer<T> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Apply data to controller via lambda function that allows access to controller methods 
            // Could be better ways to pass data between scenes idk
            if (controllerConsumer != null) {
                T controller = loader.getController();
                controllerConsumer.accept(controller);
            }

            Scene scene = new Scene(root);
            currStage.setScene(scene);
            currStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
