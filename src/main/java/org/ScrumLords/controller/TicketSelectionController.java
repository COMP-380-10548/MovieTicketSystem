package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Seat;
import org.ScrumLords.model.Seat.SeatStatus;
import org.ScrumLords.model.Showtime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

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

    private List<Seat> selectedSeats = new ArrayList();

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.<MovieShowtimesController>switchToScene(
            "/org/ScrumLords/view/MovieShowtimes.fxml",
            controller -> controller.setMovieShowtimes(movie, movie.getShowtimes())
        );
    }

    public void setMovieAndShowtime(Movie movie, Showtime showtime) {
        this.movie = movie;
        this.showtime = showtime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        movieTitleLabel.setText(movie.getTitle() + " Ticket Selection");
        showtimeLabel.setText(showtime.getStartTime().format(formatter));

        generateSeatGrid();
    }

    private void generateSeatGrid() {
        seatGrid.getChildren().clear();

        for(int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {

                String seatName = String.valueOf((char) ('A' + row)) + (column + 1); 
                Seat seat = new Seat(seatName, SeatStatus.AVAILABLE);

                //TEST CODE DELETE THIS
                if (seatName.equals("A3") || seatName.equals("C5") || seatName.equals("F2")) {
                    seat.setSeatStatus(SeatStatus.TAKEN);
                }

                Button seatButton = new Button(seatName);
                seatButton.setPrefSize(40, 40);
                seatButton.setStyle("-fx-background-color: #4A90E2;");
                
                if (seat.getSeatStatus() == SeatStatus.TAKEN) {
                    seatButton.setStyle("-fx-background-color: gray;");
                } else {
                    seatButton.setStyle("-fx-background-color: #4A90E2;");
                }

                seatButton.setOnAction(event -> {
                    if (seat.getSeatStatus() == SeatStatus.AVAILABLE) {
                        seat.setSeatStatus(SeatStatus.SELECTED);
                        selectedSeats.add(seat);
                        seatButton.setStyle("-fx-background-color: #E74C3C;");
                    } else if (seat.getSeatStatus() == SeatStatus.SELECTED) {
                        seat.setSeatStatus(SeatStatus.AVAILABLE);
                        selectedSeats.remove(seat);
                        seatButton.setStyle("-fx-background-color: #4A90E2;");
                    }
                });

                seatGrid.add(seatButton, column, row);
            }
        }
    }

    public void setMovieShowtimeAndSeats(
        Movie movie,
        Showtime showtime,
        List<Seat> selectedSeats) {

    this.movie = movie;
    this.showtime = showtime;
    this.selectedSeats = new ArrayList<>(selectedSeats);

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("h:mm a");

    movieTitleLabel.setText(movie.getTitle() + " Ticket Selection");
    showtimeLabel.setText(showtime.getStartTime().format(formatter));

    generateSeatGrid();
    }

    @FXML
    public void handleConfirm(ActionEvent event) {
        if (selectedSeats.isEmpty()) {
            return;
        }

        SceneManager.<PaymentController>switchToScene(
            "/org/ScrumLords/view/Payment.fxml",
            controller -> controller.setCheckoutDetails(
                movie,
                showtime,
                selectedSeats
            )
        );
    }
}