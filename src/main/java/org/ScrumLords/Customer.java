package org.ScrumLords;

import java.util.List;

public class Customer extends User {
    private String paymentInformation;
    private List<String> bookingHistory;

    public Customer(String userId,
                String username,
                String firstName,
                String lastName,
                String email,
                String password,
                String paymentInformation,
                List<String> bookingHistory) {

    super(userId, username, firstName, lastName, email, password);

    this.paymentInformation = paymentInformation;
    this.bookingHistory = bookingHistory;
    }

    public String getPaymentInformation() {
        return paymentInformation;
    }

    public List<String> getBookingHistory() {
        return bookingHistory;
    }

    //bookTicket()

    //cancelBooking()

    //purchaseConcessions()

    //checkout()
}