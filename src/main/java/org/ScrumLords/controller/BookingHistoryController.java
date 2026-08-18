package org.ScrumLords.controller;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Seat;
import org.ScrumLords.model.Showtime;
import org.ScrumLords.BookingManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the Booking History view.
 * Displays upcoming bookings, receives newly completed bookings,
 * allows users to cancel selected bookings, and manages navigation
 * back to the main page.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-07
 */
public class BookingHistoryController {

    private Movie movie;
    private Showtime showtime;
    private List<Seat> selectedSeats = new ArrayList<>();

    @FXML
    private ListView<String> upcomingBookingsListView;

    @FXML
    private ListView<String> previousBookingsListView;

    /**
     * Initializes the Booking History view.
     * Loads bookings stored during the current application session
     * into the upcoming bookings list.
     */
    @FXML
    public void initialize() {
        upcomingBookingsListView.getItems().addAll(
            BookingManager.getBookings()
        );
    }

    /**
     * Adds a newly completed booking to the booking history.
     * Formats the selected movie, showtime, and seats for display,
     * stores the booking for the current application session, and
     * adds it to the upcoming bookings list.
     *
     * @param movie the movie associated with the new booking
     * @param showtime the selected showtime for the movie
     * @param selectedSeats the seats selected for the booking
     */
    public void setNewBooking(
            Movie movie,
            Showtime showtime,
            List<Seat> selectedSeats) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMMM d, yyyy - h:mm a");

        String seatNames = "";

        for (int i = 0; i < selectedSeats.size(); i++) {
            seatNames += selectedSeats.get(i).getSeatNumber();

            if (i < selectedSeats.size() - 1) {
                seatNames += ", ";
            }
        }

        String booking =
                movie.getTitle() + " | " +
                showtime.getStartTime().format(formatter) + " | " +
                seatNames;

        BookingManager.addBooking(booking);

        upcomingBookingsListView.getItems().add(booking);
    }

    /**
     * Returns the user from the Booking History view to the Main Page.
     *
     * @param event the button event that triggers the navigation
     */
    @FXML
    private void returnToMainPage(ActionEvent event) {
        SceneManager.switchToScene(
            "MainPage",
            null
        );
    }

    /**
     * Cancels the currently selected upcoming booking.
     * Displays an error if no booking is selected. If a booking is
     * selected, it is removed from session storage and from the
     * upcoming bookings list.
     *
     * @param event the button event that triggers the cancellation
     */
    @FXML
    private void handleCancelBooking(ActionEvent event) {

        String selectedBooking =
                upcomingBookingsListView.getSelectionModel().getSelectedItem();

        if (selectedBooking == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Booking Selected");
            alert.setHeaderText("Select a booking");
            alert.setContentText("Please select an upcoming booking to cancel.");
            alert.showAndWait();
            return;
        }

        BookingManager.removeBooking(selectedBooking);
        upcomingBookingsListView.getItems().remove(selectedBooking);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Cancelled");
        alert.setHeaderText("Booking Cancelled");
        alert.setContentText("Your booking has been cancelled.");
        alert.showAndWait();
    }
}