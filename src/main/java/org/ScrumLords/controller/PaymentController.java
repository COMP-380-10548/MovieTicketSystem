package org.ScrumLords.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Seat;
import org.ScrumLords.model.Showtime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentController {

    private Movie movie;
    private Showtime showtime;
    private List<Seat> selectedSeats;

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label showtimeLabel;

    @FXML
    private Label selectedSeatsLabel;

    @FXML
    private Label ticketCountLabel;

    @FXML
    private Label ticketTotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalCostLabel;

    @FXML
    private TextField cardholderNameField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expirationField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField billingZipField;

    @FXML
    private Button payButton;

    public void setCheckoutDetails(
            Movie movie,
            Showtime showtime,
            List<Seat> selectedSeats) {

        this.movie = movie;
        this.showtime = showtime;
        this.selectedSeats = selectedSeats;

        double ticketPrice = 12.00;
        int ticketCount = selectedSeats.size();

        double ticketTotal = ticketPrice * ticketCount;
        double salesTax = ticketTotal * 0.0725;
        double totalCost = ticketTotal + salesTax;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("h:mm a");

        String seatNames = selectedSeats.stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.joining(", "));

        movieTitleLabel.setText(movie.getTitle());

        showtimeLabel.setText(
                showtime.getStartTime().format(formatter)
        );

        selectedSeatsLabel.setText(seatNames);

        ticketCountLabel.setText(
                String.valueOf(selectedSeats.size())
        );
        ticketTotalLabel.setText(String.format("$%.2f", ticketTotal));
        taxLabel.setText(String.format("$%.2f", salesTax));
        totalCostLabel.setText(String.format("$%.2f", totalCost));
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneManager.<TicketSelectionController>switchToScene(
            "/org/ScrumLords/view/TicketSelection.fxml",
            controller -> controller.setMovieShowtimeAndSeats(
                movie,
                showtime,
                selectedSeats
            )
        );
    }

    @FXML
    public void handlePayment(ActionEvent event) {

    }
}