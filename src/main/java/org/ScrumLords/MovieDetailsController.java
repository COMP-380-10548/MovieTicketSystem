package org.ScrumLords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieDetailsController {

    @FXML
    Label movieTitleLabel, movieRatingLabel, movieRuntimeLabel, movieDetailsLabel;

    // TODO: Probably need to implement scene memory/cache to properly return to arbitrary previous page with data intact 
    public void returnToParent(ActionEvent e) {
        SceneManager.switchToScene("MovieGallery.fxml", null);
    }

    public void viewShowtimes(ActionEvent e) {
        SceneManager.switchToScene("MovieShowtimes.fxml", null);
    }
}