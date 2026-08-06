package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class AccountController {

    @FXML
    private Label usernameLabel, nameLabel, emailLabel;

    @FXML
    public void handleLogout(ActionEvent event) {
        SessionManager.release();
        SceneManager.switchToScene("MainPage",null);
    }

    @FXML
    public void handleEditProfile(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("Edit Profile is not yet available.");
        alert.showAndWait();
    }

    @FXML
    public void handleChangePassword(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("Change Password is not yet available.");
        alert.showAndWait();
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.goBack();
    }

    @FXML
    public void handleDeleteAccount(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("Account deletion is not yet available.");
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
    }
}