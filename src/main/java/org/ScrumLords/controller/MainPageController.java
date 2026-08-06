package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.SessionManager;
import org.ScrumLords.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controls the Main Page view.
 * Updates the displayed options based on the current login session
 * and provides navigation to account, login, registration, and movie gallery screens.
 *
 * @author C Wichman
 * @author A. Garcia
 * @version 1.0
 */
public class MainPageController {
    
    @FXML 
    private Label usernameLabel;

    @FXML
    private Button accountButton, loginButton, registerButton;

    /**
     * Initializes the page based on the current user session.
     * Displays account controls for logged-in users and login and
     * registration controls for guests.
     */
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

    /**
     * Opens the Movie Gallery view.
     *
     * @param event the button event
     */
    @FXML
    public void viewMovieGallery(ActionEvent event) {
        SceneManager.switchToScene("MovieGallery", null);
    }

    /**
     * Opens the Account view.
     *
     * @param event the button event
     */
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
        SceneManager.switchToScene("Register", null);
    }
}
