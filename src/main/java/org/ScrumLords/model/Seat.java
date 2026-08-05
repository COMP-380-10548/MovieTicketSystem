package org.ScrumLords.model;

public class Seat {
    private String seatNumber;
    private SeatStatus seatStatus;

    public Seat(String seatNumber, SeatStatus seatStatus) {
        this.seatNumber = seatNumber;
        this.seatStatus = seatStatus;
    }

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