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

/**
 * Controls the Ticket Selection view.
 * Generates the theater seat grid, tracks selected seats,
 * calculates the ticket total, and manages navigation.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-04
 */
public class TicketSelectionController {

    private Movie movie;
    private Showtime showtime;
 
    @FXML
    private Label movieTitleLabel, showtimeLabel, selectedSeatsLabel, totalPriceLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private GridPane seatGrid;

    /**
     * Initializes the Ticket Selection controller.
     */
    @FXML
    private void initialize() {

    }

    private List<Seat> selectedSeats = new ArrayList<>();

    /**
     * Returns the user to the Movie Showtimes view while preserving
     * the selected movie and its showtimes.
     *
     * @param event the back button event
     */
    @FXML
    public void goBack(ActionEvent event) {
        SceneManager.<MovieShowtimesController>switchToScene(
            "MovieShowtimes",
            controller -> controller.setMovieShowtimes(
                movie,
                movie.getShowtimes()
            )
        );
    }

    /**
     * Stores the selected movie and showtime, updates the view labels,
     * and generates the theater seat grid.
     *
     * @param movie the selected movie
     * @param showtime the selected showtime
     */
    public void setMovieAndShowtime(Movie movie, Showtime showtime) {
        this.movie = movie;
        this.showtime = showtime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        movieTitleLabel.setText(movie.getTitle() + " Ticket Selection");
        showtimeLabel.setText(showtime.getStartTime().format(formatter));

        generateSeatGrid();
    }

    /**
     * Updates the selected seat display, ticket total,
     * and enabled state of the confirm button.
     */
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

    /**
     * Generates a 7-by-7 grid of seat buttons and assigns each seat
     * an available, selected, or taken status.
     */
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

                // Temporary sample data for unavailable seats.
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

    /**
     * Restores the selected movie, showtime, and seats when returning
     * from the Payment view.
     *
     * @param movie the selected movie
     * @param showtime the selected showtime
     * @param selectedSeats the seats previously selected by the user
     */
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

    /**
     * Opens the Payment view and passes the selected movie,
     * showtime, and seats to its controller.
     *
     * @param event the confirm button event
     */
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