package org.ScrumLords.model;

/**
 * Represents an administrator of the Movie Ticket Booking System.
 * Administrators manage user accounts and system administration tasks.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-08-03
 */
public class Admin extends User {

    /**
     * Constructs an Admin object.
     *
     * @param userId unique identifier for the administrator
     * @param username administrator's account username
     * @param firstName administrator's first name
     * @param lastName administrator's last name
     * @param email administrator's email address
     * @param password administrator's account password
     */
    public Admin(String userId,
                String username,
                String firstName,
                String lastName,
                String email,
                String password) {

    super(userId, username, firstName, lastName, email, password);

    }

    // Future enhancements:
    // - deleteUserAccount()
    // - createUserAccount()
    // - modifyUserAccount()
}