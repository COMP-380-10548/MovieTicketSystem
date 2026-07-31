package org.ScrumLords;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MovieGalleryController {

    @FXML
    private Button returnButton;

    @FXML
    private TextField searchField;

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText();
        System.out.println("Searching for: " + searchText);
    }

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
