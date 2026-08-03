package org.ScrumLords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainPageController {

    @FXML 
    private Label usernameLabel;

    private String username;

    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText("User: " + username);
    }

    @FXML
    public void viewMovieGallery(ActionEvent e) {
        SceneManager.<MovieGalleryController>switchToScene(
            "MovieGallery.fxml",
             controller -> controller.setUsername(username)
            );
        }

    @FXML
    public void handleAccount(ActionEvent event) {
        SceneManager.<AccountController>switchToScene(
            "Account.fxml",
            controller -> controller.setUsername(username)
        );
    }
}
