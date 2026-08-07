package org.ScrumLords.model;

/**
 * Represents a seat in a movie theater.
 * Stores the seat number and its current availability status.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-04
 */
public class Seat {
    private String seatNumber;
    private SeatStatus seatStatus;

    /**
     * Constructs a Seat object.
     *
     * @param seatNumber unique seat identifier (e.g. A1, C5)
     * @param seatStatus current status of the seat
     */
    public Seat(String seatNumber, SeatStatus seatStatus) {
        this.seatNumber = seatNumber;
        this.seatStatus = seatStatus;
    }

    /**
     * Represents the possible states of a seat.
     *
     * @since 2026-08-04
     */
    public enum SeatStatus {
        AVAILABLE,
        SELECTED,
        TAKEN
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
}