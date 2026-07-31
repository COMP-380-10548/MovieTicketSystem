package org.ScrumLords;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.RowConstraints;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class MovieGalleryController {

    @FXML
    private Button returnButton;

    @FXML
    public void initialize() {
        Platform.runLater(() -> returnButton.requestFocus());
    }
    
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
