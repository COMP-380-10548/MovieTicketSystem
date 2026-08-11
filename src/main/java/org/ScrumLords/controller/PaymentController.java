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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controls the Payment view.
 * Displays checkout details, calculates the order total,
 * validates payment information, and manages navigation
 * between payment and ticket selection.
 *
 * @author A. Garcia
 * @version 1.1
 * @since 2026-08-05
 */
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

    /**
     * Stores and displays the selected movie, showtime, seats,
     * ticket subtotal, sales tax, and final total.
     *
     * @param movie the selected movie
     * @param showtime the selected showtime
     * @param selectedSeats the seats selected by the user
     */
    public void setCheckoutDetails(
            Movie movie,
            Showtime showtime,
            List<Seat> selectedSeats) {

        this.movie = movie;
        this.showtime = showtime;
        this.selectedSeats = selectedSeats;

        int ticketCount = selectedSeats.size();

        double ticketTotal = calculateTicketTotal(ticketCount);
        double salesTax = calculateSalesTax(ticketTotal);
        double totalCost = ticketTotal + salesTax;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMMM d, yyyy - h:mm a");

        String seatNames = selectedSeats.stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.joining(", "));

        movieTitleLabel.setText(movie.getTitle());

        showtimeLabel.setText(
                showtime.getStartTime().format(formatter)
        );

        selectedSeatsLabel.setText(seatNames);

        ticketCountLabel.setText(
                String.valueOf(ticketCount)
        );

        ticketTotalLabel.setText(
                String.format("$%.2f", ticketTotal)
        );

        taxLabel.setText(
                String.format("$%.2f", salesTax)
        );

        totalCostLabel.setText(
                String.format("$%.2f", totalCost)
        );
    }

    /**
     * Calculates the subtotal for the selected number of tickets.
     *
     * @param ticketCount number of tickets being purchased
     * @return the ticket subtotal
     */
    public double calculateTicketTotal(int ticketCount) {
        double ticketPrice = 12.00;

        return ticketPrice * ticketCount;
    }

    /**
     * Calculates sales tax for the ticket subtotal.
     *
     * @param ticketTotal the subtotal before tax
     * @return the calculated sales tax
     */
    public double calculateSalesTax(double ticketTotal) {
        return ticketTotal * 0.0725;
    }

    /**
     * Validates payment information.
     *
     * @param cardholderName name on the card
     * @param cardNumber 16-digit card number
     * @param cvv 3-digit CVV
     * @param expiration expiration date in MM/YY format
     * @param billingZip 5-digit billing ZIP code
     * @return an error message if invalid, otherwise null
     */
    public String validatePayment(
            String cardholderName,
            String cardNumber,
            String cvv,
            String expiration,
            String billingZip) {

        if (cardholderName.isBlank()) {
            return "Please enter the cardholder's name.";
        }

        if (!cardNumber.matches("\\d{16}")) {
            return "Please enter 16 digits for the card number with no spaces or '-'.";
        }

        if (!cvv.matches("\\d{3}")) {
            return "Please enter 3 digits for the CVV number.";
        }

        if (!expiration.matches("\\d{2}/\\d{2}")) {
            return "Please enter the expiration date in MM/YY format.";
        }

        if (!billingZip.matches("\\d{5}")) {
            return "Please enter 5 digits for the ZIP code.";
        }

        return null;
    }

    /**
     * Returns the user to the Ticket Selection view while preserving
     * the selected movie, showtime, and seats.
     *
     * @param event the back button event
     */
    @FXML
    public void handleBack(ActionEvent event) {

        SceneManager.<TicketSelectionController>switchToScene(
                "TicketSelection",
                controller -> controller.setMovieShowtimeAndSeats(
                        movie,
                        showtime,
                        selectedSeats
                )
        );
    }

    /**
     * Processes the payment form submission.
     * Payment processing is not yet implemented.
     *
     * @param event the payment button event
     */
    @FXML
    public void handlePayment(ActionEvent event) {

        String validationError = validatePayment(
                cardholderNameField.getText(),
                cardNumberField.getText(),
                cvvField.getText(),
                expirationField.getText(),
                billingZipField.getText()
        );

        if (validationError != null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid Payment");
            alert.setHeaderText("Invalid Payment Information");
            alert.setContentText(validationError);

            alert.showAndWait();

            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Booking success");
        alert.setHeaderText("Payment Received");

        alert.setContentText(
                "Payment has been received, please proceed to view your booking."
        );

        alert.showAndWait();

        SceneManager.<BookingHistoryController>switchToScene(
                "BookingHistory",
                controller -> controller.setNewBooking(
                        movie,
                        showtime,
                        selectedSeats
                )
        );
    }

    public void initialize() {

    }
}