package org.ScrumLords;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label titleLabel = new Label("Movie Ticket System Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        Label statusLabel = new Label("Status: waiting for login...");

        loginButton.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isBlank()) {
                statusLabel.setText("Error: Username required");
                return;
            }

            if (password.isBlank()) {
                statusLabel.setText("Error: Password required");
                return;
            }

            statusLabel.setText("Validation successful");
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                titleLabel,
                usernameField,
                passwordField,
                loginButton,
                statusLabel
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 350);
        titleLabel.setStyle("-fx-font-size: 18px;");

        stage.setTitle("Movie Ticket System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}