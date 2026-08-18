package org.ScrumLords;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages bookings created during the current application session.
 * Provides methods for adding, retrieving, and removing bookings
 * so booking information can be preserved across scene changes.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-18
 */
public class BookingManager {

    private static final List<String> bookings = new ArrayList<>();

    /**
    * Prevents the BookingManager class from being instantiated.
    */
    private BookingManager() {
    }

    /**
    * Adds a new booking to the current session's booking list.
    *
    * @param booking the formatted booking information to store
    */
    public static void addBooking(String booking) {
        bookings.add(booking);
    }

    /**
    * Returns the bookings stored during the current application session.
    *
    * @return the list of currently stored bookings
    */
    public static List<String> getBookings() {
        return bookings;
    }

    /**
    * Removes a booking from the current session's booking list.
    *
    * @param booking the booking to remove
    */
    public static void removeBooking(String booking) {
        bookings.remove(booking);
    }
}