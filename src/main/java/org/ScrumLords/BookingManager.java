package org.ScrumLords;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private static final List<String> bookings = new ArrayList<>();

    private BookingManager() {
    }

    public static void addBooking(String booking) {
        bookings.add(booking);
    }

    public static List<String> getBookings() {
        return bookings;
    }
}