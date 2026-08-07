package org.ScrumLords;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

/**
 * Static utilities that manages the JavaFX Stage's active scenes.
 * Allows scene switching by name and scene history management through a stack.
 */
public final class SceneManager {

    private static Stage currStage;
    private static final Deque<String> history = new ArrayDeque<>();

    private SceneManager() {}

    /**
     * Initiate a primary stage for scene management
     * @param stage A stage to manage
     */
    public static void init(Stage stage) {
        currStage = stage;
    }

    /**
     * Change the currently displayed scene within the frontend's stage.
     * @param <T> The class of the scene to switch into when a consumer is provided.
     * @param scenePath A String of the scene name from /org/ScrumLords/view to switch to. The ".fxml" extension is not needed.
     * @param controllerConsumer An optional lambda function that can access data within the next scene's controller. Useful for passing data directly between controllers.
     */
    public static <T> void switchToScene(String scenePath, Consumer<T> controllerConsumer) {
        loadScene(scenePath, controllerConsumer);
        history.push(scenePath);
    }

    /**
     * Switch back to the previous scene stored in scene history.
     */
    public static void goBack() {
        if(history.isEmpty())
            return;
        
        history.pop();
        String previousScene = history.peek();
        
        if(previousScene != null)
            loadScene(previousScene, null);
    }

    /**
     * Clear the scene history. Useful when the app navigates back directly to MainPage.
     */
    public static void clearHistory() {
        history.clear();
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
