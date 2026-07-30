package org.ScrumLords;

import java.time.LocalDateTime;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MovieDetailsController {

    @FXML
    Label movieTitleLabel, movieRatingLabel, movieRuntimeLabel, movieDetailsLabel;

    private Movie movie;
    private List<Showtime> showtimes;

    // TODO: Probably need to implement scene memory/cache
    // to properly return to arbitrary previous page with data intact
    public void returnToParent(ActionEvent e) {
        SceneManager.switchToScene("MovieGallery.fxml", null);
    }

    public void viewShowtimes(ActionEvent e) {
        SceneManager.<MovieShowtimesController>switchToScene(
            "MovieShowtimes.fxml",
            controller -> {
                controller.setMovieShowtimes(movie, showtimes);
            }
        );
    }

    @FXML
    public void initialize() {
        movie = new Movie(
            "Interstellar",
            "PG-13",
            "2h 49m",
            "A team of explorers travels through a wormhole in space."
        );

        showtimes = List.of(
            new Showtime(
                1,
                1,
                LocalDateTime.of(2026, 7, 29, 18, 30),
                LocalDateTime.of(2026, 7, 29, 21, 19)
            )
        );

        movieTitleLabel.setText("Title: " + movie.getTitle());
        movieRatingLabel.setText("Rating: " + movie.getRating());
        movieRuntimeLabel.setText("Runtime: " + movie.getRuntime());
        movieDetailsLabel.setText("Description: " + movie.getDescription());
    }
}