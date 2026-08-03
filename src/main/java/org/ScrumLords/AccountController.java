package org.ScrumLords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javafx.scene.control.Alert;

public class AccountController {

    private String username;

    @FXML
    private Label usernameLabel, nameLabel, emailLabel;

    @FXML
    public void handleLogout(ActionEvent event) {
        SceneManager.switchToScene("Login.fxml",null);
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
        SceneManager.<MainPageController>switchToScene(
            "MainPage.fxml", 
            controller -> controller.setUsername(username)
        );
    }

    @FXML
    public void handleDeleteAccount(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("Account deletion is not yet available.");
        alert.showAndWait();
    }

    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText(username);
    }

    @FXML
    public void initialize() {
    }
}