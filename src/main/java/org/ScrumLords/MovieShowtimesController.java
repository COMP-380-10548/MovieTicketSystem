package org.ScrumLords;

import java.util.List;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class MovieShowtimesController {

    // example of array of showtimes to display for a movie. can be reworked.
    private List<Showtime> showtimes;

    private Movie movie;

    @FXML
    private Label movieTitle, movieShowtimes;

    @FXML
    private void initialize() {
        movieShowtimes.setText("No showtimes are currently available.");
    }

    private void displayShowtimes() {
        if (showtimes == null || showtimes.isEmpty()) {
            movieShowtimes.setText("No showtimes are currently available.");
            return;
        }

        StringBuilder text = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        for (Showtime showtime : showtimes) {
            text.append(showtime.getStartTime().format(formatter))
                .append(" - ")
                .append(showtime.getEndTime().format(formatter))
                .append(System.lineSeparator());
        }

        movieShowtimes.setText(text.toString());
    }

    public void returnToParent(ActionEvent event) {
        SceneManager.<MovieDetailsController>switchToScene(
            "MovieDetails.fxml",
            controller -> controller.setMovie(movie)
        );
    }

    public void setMovieShowtimes(Movie movie, List<Showtime> showtimes) {
        this.movie = movie;
        this.showtimes = showtimes;
        movieTitle.setText(movie.getTitle());
        displayShowtimes();
    }
}
