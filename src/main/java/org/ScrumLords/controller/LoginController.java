package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.SessionManager;
import org.ScrumLords.UserService;
import org.ScrumLords.model.User;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controls the Login view.
 * Authenticates user credentials against the UserService and
 * stores the resulting user in the session on success.
 *
 * @author C Wichman
 * @version 1.0
 * @since 2026-06-25
 */
public class LoginController {

    private final UserService userService = new UserService();
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;

    public void submitLogin(ActionEvent e) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        loginButton.setDisable(true);
        usernameField.setDisable(true);
        passwordField.setDisable(true);
        statusLabel.setText("Status: validating login...");

        // Task object that defines what the thread returns
        // In this case: a User from the userService
        Task<User> loginTask = new Task<>() {
            @Override
            protected User call() {
                return userService.authenticate(username, password);
            }
        };

        // When thread finishes, manage returned User
        loginTask.setOnSucceeded(event -> {
           User user = loginTask.getValue();
           if (user != null) {
            SessionManager.store(user);
            SceneManager.switchToScene("MainPage", null);
           } else {
            loginButton.setDisable(false);
            usernameField.setDisable(false);
            passwordField.setDisable(false);
            statusLabel.setText("Status: invalid login.");
           }
        });

        // When thread fails, return failure
        loginTask.setOnFailed(event -> {
            loginButton.setDisable(false);
            usernameField.setDisable(false);
            passwordField.setDisable(false);
            statusLabel.setText("Status: cannot connect to server");
        });

        // Starts the thread
        new Thread(loginTask).start();
    }

    public void goBack(ActionEvent e) {
        SceneManager.goBack();
    }
}