package org.ScrumLords;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MovieShowtimesController {

    // example of array of showtimes to display for a movie. can be reworked
    private LocalDate[] movieTimes;

    @FXML
    private Label movieTitle, movieShowtimes;

    @FXML
    private void initialize() {
        if(movieTimes == null) {
            movieShowtimes.setText("No showtimes are currently available.");
        } else {
            String movieShowtimes = "";
            for (int i = 0; i < movieTimes.length; ++i) {
                movieShowtimes += movieTimes[i].toString();
            }
            this.movieShowtimes.setText(movieShowtimes);
        }
    }

    public void returnToParent(ActionEvent e) {
        SceneManager.switchToScene("MovieDetails.fxml", null);
    }
}
