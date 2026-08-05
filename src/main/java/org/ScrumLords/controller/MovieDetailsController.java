package org.ScrumLords.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Showtime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieDetailsController {

    @FXML
    Label movieTitleLabel, movieRatingLabel, movieRuntimeLabel, movieDetailsLabel;

    private Movie movie;
    private List<Showtime> showtimes;

    // to properly return to arbitrary previous page with data intact
    public void goBack(ActionEvent e) {
        SceneManager.goBack();
    }

    public void viewShowtimes(ActionEvent e) {
        SceneManager.<MovieShowtimesController>switchToScene(
            "MovieShowtimes",
            controller -> {
                controller.setMovieShowtimes(movie, movie.getShowtimes());
            }
        );
    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        movieTitleLabel.setText("Title: " + movie.getTitle());
        movieRatingLabel.setText("Rating: " + movie.getRating());
        movieRuntimeLabel.setText("Runtime: " + movie.getRuntime());
        movieDetailsLabel.setText("Description: " + movie.getDescription());
    }

    @FXML
    public void initialize() {
    }
}