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
    public void goBack(ActionEvent event) {
        SceneManager.goBack();
    }

    @FXML
    public void handleConfirm(ActionEvent event) {

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
                    if (seat.getSeatStatus() == SeatStatus.TAKEN) {
                        return;
                    }
                    if (seat.getSeatStatus() == SeatStatus.AVAILABLE) {
                        seat.setSeatStatus(SeatStatus.SELECTED);
                        seatButton.setStyle("-fx-background-color: #E74C3C;");
                    } else {
                        seat.setSeatStatus(SeatStatus.AVAILABLE);
                        seatButton.setStyle("-fx-background-color: #4A90E2;");
                    }
                });

                seatGrid.add(seatButton, column, row);
            }
        }
    }
}
