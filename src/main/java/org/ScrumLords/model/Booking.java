package org.ScrumLords.model;

import java.util.List;

/**
 * Represents a movie ticket booking made by a customer.
 * Stores booking information, selected seats, payment details,
 * and the current booking status.
 *
 * @author A. Garcia
 * @version 1.0
 */
public class Booking {
    private int bookingId;
    private int showtimeId;
    private int userId;
    private List<String> seatNumbers;
    private double totalPrice;
    private String transactionId;
    private String bookingStatus;

    /**
     * Constructs a Booking object.
     *
     * @param bookingId unique identifier for the booking
     * @param showtimeId identifier of the booked showtime
     * @param userId identifier of the customer who made the booking
     * @param seatNumbers list of reserved seat numbers
     * @param totalPrice total cost of the booking
     * @param transactionId payment transaction identifier
     * @param bookingStatus current status of the booking
     */
    public Booking(int bookingId, int showtimeId, int userId, List<String> seatNumbers, double totalPrice, String transactionId, String bookingStatus) {
        this.bookingId = bookingId;
        this.showtimeId = showtimeId;
        this.userId = userId;
        this.seatNumbers = seatNumbers;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public int getUserId() {
        return userId;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    // Future enhancements:
    // - viewBookingDetails()
    // - generateTicket()
}