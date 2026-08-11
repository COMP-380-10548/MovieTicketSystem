package org.ScrumLords.model;

import java.util.List;

/**
 * Represents a customer of the Movie Ticket Booking System.
 * Customers can purchase tickets and maintain a booking history.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-03
 */
public class Customer extends User {
    private List<Booking> bookingHistory;

    /**
     * Constructs a Customer object.
     *
     * @param userId unique identifier for the customer
     * @param username customer's account username
     * @param firstName customer's first name
     * @param lastName customer's last name
     * @param email customer's email address
     * @param password customer's account password
     * @param bookingHistory list of the customer's previous bookings
     */
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

    // Future enhancements:
    // - bookTicket()
    // - cancelBooking()
    // - purchaseConcessions()
    // - checkout()
}