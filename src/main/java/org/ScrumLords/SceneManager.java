package org.ScrumLords;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class SceneManager {

    private static Stage currStage;
    private static final Deque<String> history = new ArrayDeque<>();

    public static void init(Stage stage) {
        currStage = stage;
    }

    public static <T> void switchToScene(String scenePath, Consumer<T> controllerConsumer) {
        loadScene(scenePath, controllerConsumer);
        history.push(scenePath);
    }

    public static void goBack() {

        if(history.isEmpty())
            return;
        
        history.pop();
        String previousScene = history.peek();
        
        if(previousScene != null)
            loadScene(previousScene, null);
    }

    private static <T> void loadScene(String scenePath, Consumer<T> controllerConsumer) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/ScrumLords/view/" + scenePath + ".fxml"));
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
