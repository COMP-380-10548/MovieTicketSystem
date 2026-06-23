package org.ScrumLords;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
            stage.setScene(createMainPageScene(stage));
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

    private Scene createMainPageScene(Stage stage) {
        Label titleLabel = new Label("Main Page");

        Button viewMovieDetails = new Button("View Movie Details");

        viewMovieDetails.setOnAction(e -> {
            stage.setScene(createMovieDetailsScene(stage));
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                titleLabel,
                viewMovieDetails
        );      

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 350);
        titleLabel.setStyle("-fx-font-size: 18px;");

        return scene;
    }

    private Scene createMovieDetailsScene(Stage stage) {
        Label titleLabel = new Label("Movie Details Screen");

        Label movieTitle = new Label("Title: Interstellar");

        Label movieRating = new Label("Rating: PG-13");

        Label movieRuntime = new Label("Runtime: 169 minutes");

        Button backButton = new Button("Back");

        Button viewShowtimes = new Button("View Showtimes");

        backButton.setOnAction(e -> {
            stage.setScene(createMainPageScene(stage));
        });


        Label movieDescription = new Label("Description: A team of explorers travel through a wormhole in space.");
        movieDescription.setWrapText(true);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                titleLabel,
                movieTitle,
                movieRating,
                movieRuntime,
                backButton,
                viewShowtimes
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 350);
        titleLabel.setStyle("-fx-font-size: 18px;");

        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

}