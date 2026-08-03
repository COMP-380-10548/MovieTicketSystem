package org.ScrumLords;

public class Ticket {
    private int ticketId;
    private int bookingId;
    private String seatNumber;
    private double price;

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

    //reserveSeat()
    //setSeatNumber()
    //setTicketPrice()
}