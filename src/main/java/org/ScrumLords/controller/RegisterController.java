package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.UserService;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    public void submitRegistration(ActionEvent e) {

        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        isRegistring(true);

        Task<Boolean> registerTask = new Task<>() {
            @Override
            protected Boolean call() {
                return userService.register(username, firstName, lastName, email, password);
            }
        };

        registerTask.setOnSucceeded(event -> {
            if(registerTask.getValue()) {
                SceneManager.switchToScene("MainPage", null);
            } else {
                isRegistring(false);
                statusLabel.setText("Error: invalid inputs!");
            }
        });

        registerTask.setOnFailed(event -> {
            isRegistring(false);
            statusLabel.setText("Error: cannot connect to database!");
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
}
