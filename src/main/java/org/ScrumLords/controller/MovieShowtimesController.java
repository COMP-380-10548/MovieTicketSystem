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

/**
 * Controls the Movie Showtimes view.
 * Displays the selected movie's available showtimes and allows
 * the user to continue to ticket selection.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-06-29
 */
public class MovieShowtimesController {

    private List<Showtime> showtimes;

    private Movie movie;

    @FXML
    private Label movieTitle;

    @FXML
    private VBox showtimeContainer;

    /**
     * Initializes the Movie Showtimes controller.
     */
    @FXML
    private void initialize() {
        
    }

    /**
     * Creates and displays one button for each available showtime.
     * Selecting a showtime opens the Ticket Selection view and passes
     * the selected movie and showtime to its controller.
     */
    private void displayShowtimes() {
        showtimeContainer.getChildren().clear();

        if (showtimes == null || showtimes.isEmpty()) {
            showtimeContainer.getChildren().add(
                new Label("No showtimes are currently available.")
            );
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - h:mm a");

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

    /**
     * Returns the user to the Movie Details view while preserving
     * the selected movie.
     *
     * @param event the back button event
     */
    @FXML
    public void goBack(ActionEvent event) {
        SceneManager.<MovieDetailsController>switchToScene(
            "MovieDetails",
            controller -> controller.setMovie(movie)
        );
    }

    /**
     * Stores the selected movie and its showtimes, updates the movie
     * title, and displays the available showtime buttons.
     *
     * @param movie the selected movie
     * @param showtimes the movie's available showtimes
     */
    public void setMovieShowtimes(Movie movie, List<Showtime> showtimes) {
        this.movie = movie;
        this.showtimes = showtimes;
        movieTitle.setText(movie.getTitle());
        displayShowtimes();
    }
}
