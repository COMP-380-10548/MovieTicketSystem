package org.ScrumLords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainPageController {

    @FXML 
    Label usernameLabel;

    public void setUsername(String username) {
        usernameLabel.setText("User: " + username);
    }

    public void viewMovieGallery(ActionEvent e) {
        SceneManager.switchToScene("MovieGallery.fxml", null);
    }
}
