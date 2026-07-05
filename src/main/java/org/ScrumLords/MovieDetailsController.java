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
        Movie movie = new Movie("Interstellar",
            "PG-13",
            "2h 49m",
            "A team of explorers travels through a wormhole in space."
        );

        movieTitleLabel.setText("Title: " + movie.getTitle());
        movieRatingLabel.setText("Rating: " + movie.getRating());
        movieRuntimeLabel.setText("Runtime: " + movie.getRuntime());
        movieDetailsLabel.setText("Description: " + movie.getDescription());
    }
}