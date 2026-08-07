package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Showtime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controls the Movie Details view.
 * Displays information about a selected movie and
 * allows users to navigate to available showtimes.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-06-28
 */
public class MovieDetailsController {

    @FXML
    Label movieTitleLabel, movieRatingLabel, movieRuntimeLabel, movieDetailsLabel;

    private Movie movie;

    /**
     * Returns the user to the Movie Gallery screen.
     *
     * @param event the button click event
     */
    @FXML
    public void goBack(ActionEvent event) {
        SceneManager.switchToScene(
            "MovieGallery",
            null
        );
    }

    /**
     * Opens the Movie Showtimes screen and passes
     * the selected movie and its available showtimes.
     *
     * @param event the button click event
     */
    @FXML
    public void viewShowtimes(ActionEvent event) {
        SceneManager.<MovieShowtimesController>switchToScene(
            "MovieShowtimes",
            controller -> {
                controller.setMovieShowtimes(movie, movie.getShowtimes());
            }
        );
    }

    /**
     * Populates the Movie Details screen with the
     * selected movie's information.
     *
     * @param movie the selected movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;

        movieTitleLabel.setText("Title: " + movie.getTitle());
        movieRatingLabel.setText("Rating: " + movie.getRating());
        movieRuntimeLabel.setText("Runtime: " + movie.getRuntime());
        movieDetailsLabel.setText("Description: " + movie.getDescription());
    }

    /**
     * Initializes the Movie Details controller.
     */
    @FXML
    public void initialize() {
    }
}