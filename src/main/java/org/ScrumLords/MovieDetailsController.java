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

    //Temp placeholder data until we implement a database to pull from
    @FXML
    public void initialize() {
        movieTitleLabel.setText("Title: Interstellar");
        movieRatingLabel.setText("Rating: PG-13");
        movieRuntimeLabel.setText("Runtime: 2h 49m");
        movieDetailsLabel.setText("Description: A team of explorers travels through a wormhole in space.");
    }
}