package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.SessionManager;
import org.ScrumLords.UserService;
import org.ScrumLords.model.Customer;
import org.ScrumLords.model.User;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AccountController {
    private User currUser = SessionManager.getCurrentUser();
    private UserService userService = new UserService();

    @FXML
    private Label usernameLabel, nameLabel, emailLabel;

    @FXML
    private TextField firstNameField, lastNameField, emailField;

    @FXML
    private Button editButton, submitButton, cancelButton, backButton, logoutButton, manageButton;

    @FXML
    private HBox editButtonBox;

    @FXML
    public void initialize() {
        usernameLabel.setText(currUser.getUsername());
        nameLabel.setText(currUser.getFirstName() + " " + currUser.getLastName());
        emailLabel.setText(currUser.getEmail());

        editButtonBox.setVisible(false);
        editButtonBox.setManaged(false);

        if(currUser instanceof Customer) {
            manageButton.setVisible(false);
            manageButton.setManaged(false);
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        SessionManager.release();
        SceneManager.switchToScene("MainPage",null);
    }

    @FXML
    public void handleEditProfile(ActionEvent event) {
        firstNameField.setText(currUser.getFirstName());
        lastNameField.setText(currUser.getLastName());
        emailField.setText(currUser.getEmail());

        isEditing(true);
    }

    @FXML
    public void handleSubmitProfile(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        isBusy(true);

        Task<User> updateTask = new Task<>() {
            @Override
            protected User call() {
                return userService.updateProfile(currUser.getUserId(), firstName, lastName, email);
            }
        };

        updateTask.setOnSucceeded(e -> {
            User updatedUser = updateTask.getValue();
            isBusy(false);

            if(updatedUser == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid profile input!");
                alert.showAndWait();
            } else {
                SessionManager.store(updatedUser);
                currUser = SessionManager.getCurrentUser();

                nameLabel.setText(currUser.getFirstName() + " " + currUser.getLastName());
                emailLabel.setText(currUser.getEmail());
                isEditing(false);
            }
        });

        updateTask.setOnFailed(e -> {
            isBusy(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Failed");
            alert.setHeaderText(null);
            alert.setContentText("Error: Cannot connect to server!");
            alert.showAndWait();
        });

        new Thread(updateTask).start();
    }

    @FXML
    public void handleCancelEdit(ActionEvent event) {
        isEditing(false);
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
    public void launchManage(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feature Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("The management page is not yet available.");
        alert.showAndWait();
    }

    private void isEditing(boolean state) {
        nameLabel.setVisible(!state);
        emailLabel.setVisible(!state);

        firstNameField.setVisible(state);
        firstNameField.setDisable(!state);
        lastNameField.setVisible(state);
        lastNameField.setDisable(!state);
        emailField.setVisible(state);
        emailField.setDisable(!state);

        editButton.setVisible(!state);
        editButton.setManaged(!state);
        editButtonBox.setVisible(state);
        editButtonBox.setManaged(state);
    }

    private void isBusy(boolean state) {
        submitButton.setDisable(state);
        cancelButton.setDisable(state);
        backButton.setDisable(state);
        logoutButton.setDisable(state);
    }
}