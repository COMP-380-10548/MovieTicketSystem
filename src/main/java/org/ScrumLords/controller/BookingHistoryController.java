package org.ScrumLords.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Seat;
import org.ScrumLords.model.Showtime;

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
            "Superman | Aug 8 | 7:00 PM | A1, A2",
            "Fantastic Four | Aug 10 | 6:30 PM | C4"
        );

        previousBookingsListView.getItems().addAll(
            "Dune | Jul 28 | 8:00 PM | F5",
            "Deadpool | Jul 20 | 5:30 PM | B2, B3"
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