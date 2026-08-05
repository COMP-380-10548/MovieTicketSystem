package org.ScrumLords.controller;

import java.util.List;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Showtime;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class MovieShowtimesController {

    // example of array of showtimes to display for a movie. can be reworked.
    private List<Showtime> showtimes;

    private Movie movie;

    @FXML
    private Label movieTitle;

    @FXML
    private VBox showtimeContainer;

    @FXML
    private void initialize() {
        
    }

    private void displayShowtimes() {
        showtimeContainer.getChildren().clear();

        if (showtimes == null || showtimes.isEmpty()) {
            showtimeContainer.getChildren().add(
                new Label("No showtimes are currently available.")
            );
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        for (Showtime showtime : showtimes) {
            Button showtimeButton = new Button(
                showtime.getStartTime().format(formatter)
            );

            showtimeButton.setOnAction(event -> {
                SceneManager.<TicketSelectionController>switchToScene(
                    "TicketSelection",
                     controller -> controller.setMovieAndShowtime(movie, showtime)
                    );
            });

            showtimeContainer.getChildren().add(showtimeButton);
        }
    }

    public void goBack(ActionEvent event) {
        SceneManager.goBack();
    }

    public void setMovieShowtimes(Movie movie, List<Showtime> showtimes) {
        this.movie = movie;
        this.showtimes = showtimes;
        movieTitle.setText(movie.getTitle());
        displayShowtimes();
    }
}
