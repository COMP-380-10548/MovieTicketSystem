package org.ScrumLords.model;

import java.util.List;

public class Customer extends User {
    private List<Booking> bookingHistory;

    public Customer(String userId,
                String username,
                String firstName,
                String lastName,
                String email,
                String password,
                List<Booking> bookingHistory) {

    super(userId, username, firstName, lastName, email, password);

    this.bookingHistory = bookingHistory;
    }

    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    //bookTicket()

    //cancelBooking()

    //purchaseConcessions()

    //checkout()
}