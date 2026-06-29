package org.ScrumLords;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.RowConstraints;

public class MovieGalleryController {
    
    @FXML
    private GridPane movieGallery;

    // TODO: use setUserData to associate each movie item with a specific movie
    public void viewMovieDetails(ActionEvent e) {
       SceneManager.switchToScene("MovieDetails.fxml", null); 
    }

    public void returnToMainPage(ActionEvent e) {
        SceneManager.switchToScene("MainPage.fxml", null);
    }
}
