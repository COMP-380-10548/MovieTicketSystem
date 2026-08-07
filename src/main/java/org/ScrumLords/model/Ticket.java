package org.ScrumLords.model;

/**
 * Represents a movie ticket purchased for a booking.
 * Stores the ticket identifier, associated booking,
 * assigned seat, and ticket price.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-03
 */
public class Ticket {
    private int ticketId;
    private int bookingId;
    private String seatNumber;
    private double price;

    /**
     * Constructs a Ticket object.
     *
     * @param ticketId unique identifier for the ticket
     * @param bookingId identifier of the associated booking
     * @param seatNumber assigned seat number
     * @param price ticket price
     */
    public Ticket(int ticketId, int bookingId, String seatNumber, double price) {
        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    // Future enhancements:
    // - reserveSeat()
    // - setSeatNumber()
    // - setTicketPrice()
}