package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.SessionManager;
import org.ScrumLords.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainPageController {
    
    @FXML 
    private Label usernameLabel;

    @FXML
    private Button accountButton, loginButton, registerButton;

    @FXML
    public void initialize() {
        User user = SessionManager.getCurrentUser();
        boolean loggedIn = user != null;

        accountButton.setVisible(loggedIn);
        accountButton.setManaged(loggedIn);

        loginButton.setVisible(!loggedIn);
        loginButton.setManaged(!loggedIn);
        registerButton.setVisible(!loggedIn);
        registerButton.setManaged(!loggedIn);


        if(loggedIn) {
            usernameLabel.setText(user.getFirstName() + " " + user.getLastName());
        } else {
            usernameLabel.setText("Guest");
        }
    }

    @FXML
    public void viewMovieGallery(ActionEvent e) {
        SceneManager.switchToScene("MovieGallery", null);
    }

    @FXML
    public void handleAccount(ActionEvent event) {
        SceneManager.switchToScene("Account", null);
    }

    @FXML
    public void startLogin(ActionEvent event) {
        SceneManager.switchToScene("Login", null);
    }

    @FXML
    public void startRegister(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("Account registration is not yet available.");
        alert.showAndWait();
    }
}
