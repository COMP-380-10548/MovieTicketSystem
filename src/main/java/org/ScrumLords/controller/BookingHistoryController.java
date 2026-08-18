package org.ScrumLords.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Seat;
import org.ScrumLords.model.Showtime;
import org.ScrumLords.BookingManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingHistoryController {

    private Movie movie;
    private Showtime showtime;
    private List<Seat> selectedSeats = new ArrayList<>();

    @FXML
    private ListView<String> upcomingBookingsListView;

    @FXML
    private ListView<String> previousBookingsListView;

    @FXML
    public void initialize() {
        upcomingBookingsListView.getItems().addAll(
            BookingManager.getBookings()
        );
    }

 
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

    @FXML
    private void returnToMainPage(ActionEvent event) {
        SceneManager.switchToScene(
            "MainPage",
            null
        );
    }

    @FXML
    private void handleCancelBooking(ActionEvent event) {

    }


}