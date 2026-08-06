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
import java.util.stream.Collectors;

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

    private List<Seat> selectedSeats = new ArrayList<>();

    @FXML
    public void goBack(ActionEvent event) {
        SceneManager.goBack();
    }

    public void setMovieAndShowtime(Movie movie, Showtime showtime) {
        this.movie = movie;
        this.showtime = showtime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        movieTitleLabel.setText(movie.getTitle() + " Ticket Selection");
        showtimeLabel.setText(showtime.getStartTime().format(formatter));

        generateSeatGrid();
    }

    private void updateSelectionSummary() {
        String seatNames = selectedSeats.stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.joining(", "));

        if (selectedSeats.isEmpty()) {
            selectedSeatsLabel.setText("Selected seats: None");
        } else {
            selectedSeatsLabel.setText("Selected seats: " + seatNames);
        }

        double ticketPrice = 12.00;
        double total = selectedSeats.size() * ticketPrice;

        totalPriceLabel.setText(String.format("Total: $%.2f", total));
        confirmButton.setDisable(selectedSeats.isEmpty());
    }

    private void generateSeatGrid() {
        seatGrid.getChildren().clear();

        for(int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {

                String seatName = String.valueOf((char) ('A' + row)) + (column + 1); 
                Seat seat = selectedSeats.stream()
                    .filter(selectedSeat ->
                            selectedSeat.getSeatNumber().equals(seatName))
                    .findFirst()
                    .orElse(new Seat(seatName, SeatStatus.AVAILABLE));

                Button seatButton = new Button(seatName);
                seatButton.setPrefSize(40, 40);

                //TEST CODE DELETE THIS
                if (seatName.equals("A3") || seatName.equals("C5") || seatName.equals("F2")) {
                    seat.setSeatStatus(SeatStatus.TAKEN);
                }

                if (seat.getSeatStatus() == SeatStatus.TAKEN) {
                    seatButton.setStyle("-fx-background-color: gray;");
                } else if (seat.getSeatStatus() == SeatStatus.SELECTED) {
                    seatButton.setStyle("-fx-background-color: #E74C3C;");
                } else {
                    seatButton.setStyle("-fx-background-color: #4A90E2;");
                }

                seatButton.setOnAction(event -> {
                    if (seat.getSeatStatus() == SeatStatus.TAKEN) {
                        return;
                    }

                    if (seat.getSeatStatus() == SeatStatus.AVAILABLE) {
                        seat.setSeatStatus(SeatStatus.SELECTED);

                        if (!selectedSeats.contains(seat)) {
                            selectedSeats.add(seat);
                        }

                        seatButton.setStyle("-fx-background-color: #E74C3C;");
                    } else {
                        seat.setSeatStatus(SeatStatus.AVAILABLE);
                        selectedSeats.remove(seat);
                        seatButton.setStyle("-fx-background-color: #4A90E2;");
                    }

                    updateSelectionSummary();
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
    updateSelectionSummary();
    }

    @FXML
    public void handleConfirm(ActionEvent event) {
        if (selectedSeats.isEmpty()) {
            return;
        }

        SceneManager.<PaymentController>switchToScene(
            "Payment",
            controller -> controller.setCheckoutDetails(
                movie,
                showtime,
                selectedSeats
            )
        );
    }
}