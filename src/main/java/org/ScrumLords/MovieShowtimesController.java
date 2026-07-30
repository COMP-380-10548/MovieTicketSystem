package org.ScrumLords;

import java.util.List;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MovieShowtimesController {

    // example of array of showtimes to display for a movie. can be reworked.
    private List<Showtime> showtimes;

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

        for (Showtime showtime : showtimes) {
            text.append(showtime.getStartTime())
                .append(" - ")
                .append(showtime.getEndTime())
                .append(System.lineSeparator());
        }

        movieShowtimes.setText(text.toString());
    }

    public void returnToParent(ActionEvent e) {
        SceneManager.switchToScene("MovieDetails.fxml", null);
    }

    public void setMovieShowtimes(Movie movie, List<Showtime> showtimes) {
        this.showtimes = showtimes;
        movieTitle.setText(movie.getTitle());
        displayShowtimes();
    }
}
