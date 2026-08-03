package org.ScrumLords;

import java.util.List;

public class Booking {
    private int bookingId;
    private int showtimeId;
    private int userId;
    private List<String> seatNumbers;
    private double totalPrice;
    private String transactionId;
    private String bookingStatus;

    public Booking(int bookingId, int showtimeId, int userId, List<String> seatNumbers, double totalPrice, String transactionId, String bookingStatus) {
        this.bookingId = bookingId;
        this.showtimeId = showtimeId;
        this.userId = userId;
        this.seatNumbers = seatNumbers;
        this.totalPrice = totalPrice;
        this.transactionId = transactionId;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() { //maybe not needed
        return bookingId;
    }

    public int getShowtimeId() { //maybe not needed
        return showtimeId;
    }

    public int getUserId() { //maybe not needed
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

    //viewBookingDetails()
    //generateTicket()
}