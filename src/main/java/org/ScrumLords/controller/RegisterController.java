package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.UserService;
import org.ScrumLords.model.User;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterController {

    private final UserService userService = new UserService();

    @FXML
    private TextField usernameField, firstNameField, lastNameField, emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Label statusLabel;

    @FXML
    private ImageView logoImage;

    private javafx.scene.image.Image logo;

    public void submitRegistration(ActionEvent e) {

        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        isRegistring(true);

        Task<User> registerTask = new Task<>() {
            @Override
            protected User call() {
                return userService.register(username, firstName, lastName, email, password);
            }
        };

        registerTask.setOnSucceeded(event -> {
            User newUser = registerTask.getValue();
            if(newUser != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Registered Successfully");
                alert.setHeaderText("User Registered Successfully");
                alert.setContentText("Username: " + newUser.getUsername() + "\nEmail: " + newUser.getEmail());
                alert.showAndWait();

                SceneManager.switchToScene("MainPage", null);
                SceneManager.clearHistory();
            } else {
                isRegistring(false);
                statusLabel.setText("Error: invalid inputs!");
            }
        });

        registerTask.setOnFailed(event -> {
            isRegistring(false);
            statusLabel.setText("Error: cannot connect to server!");
        });

        new Thread(registerTask).start();
    }

    public void goBack(ActionEvent e) {
        SceneManager.goBack();
    }

    private void isRegistring(boolean state) {
        usernameField.setDisable(state);
        firstNameField.setDisable(state);
        lastNameField.setDisable(state);
        emailField.setDisable(state);
        passwordField.setDisable(state);
        registerButton.setDisable(state);
        statusLabel.setVisible(!state);
    }

    public void handleLogoClick(MouseEvent event) {
        SceneManager.switchToScene("MainPage", null);
    }
}
