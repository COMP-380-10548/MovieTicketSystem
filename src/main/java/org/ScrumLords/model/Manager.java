package org.ScrumLords.model;

/**
 * Represents a manager of the Movie Ticket Booking System.
 * Managers are responsible for maintaining movies, showtimes,
 * concession items, and viewing sales reports.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-03
 */
public class Manager extends User {

    /**
     * Constructs a Manager object.
     *
     * @param userId unique identifier for the manager
     * @param username manager's account username
     * @param firstName manager's first name
     * @param lastName manager's last name
     * @param email manager's email address
     * @param password manager's account password
     */
    public Manager(String userId,
                String username,
                String firstName,
                String lastName,
                String email,
                String password) {

    super(userId, username, firstName, lastName, email, password);

    }
    
    // Future enhancements:
    // - addMovie()
    // - deleteMovie()
    // - getTicketSalesReport()
    // - getConcessionSalesReport()
    // - updateShowtime()
    // - addConcessionItem()
    // - removeConcessionItem()
    // - modifyConcessionItem()
}