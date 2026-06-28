package org.ScrumLords;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.RowConstraints;

public class MovieGalleryController {
    
    @FXML
    private GridPane movieGallery;

    @FXML
    private Label usernameLabel;

    public void setUsername(String username) {
        usernameLabel.setText("user: " + username);
    };

    // TODO: use setUserData to associate each movie item with a specific movie
    public void viewMovieDetails(ActionEvent e) {
       SceneManager.switchToScene("MovieDetails.fxml", null); 
    }
}
