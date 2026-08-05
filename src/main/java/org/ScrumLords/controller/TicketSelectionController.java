package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Showtime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TicketSelectionController {

    private Movie movie;
    private Showtime showtime;
    
    @FXML
    private Label movieTitleLabel, showtimeLabel, selectedSeatsLabel, totalPriceLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private GridPane seatGrid;

    @FXML
    private void initialize() {

    }
    
    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.<MovieShowtimesController>switchToScene(
            "/org/ScrumLords/view/MovieShowtimes.fxml",
            controller -> controller.setMovieShowtimes(movie, movie.getShowtimes())
        );
    }

    @FXML
    public void handleConfirm(ActionEvent event) {

    }

    public void setMovieAndShowtime(Movie movie, Showtime showtime) {
        this.movie = movie;
        this.showtime = showtime;

        movieTitleLabel.setText(movie.getTitle() + " Ticket Selection");
        showtimeLabel.setText(showtime.getStartTime().toString());

        generateSeatGrid();
    }

    private void generateSeatGrid() {
        seatGrid.getChildren().clear();

        for(int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                String seatName = String.valueOf((char) ('A' + row)) + (column + 1);

                Button seatButton = new Button(seatName);
                seatGrid.add(seatButton, column, row);
            }
        }
    }
}
