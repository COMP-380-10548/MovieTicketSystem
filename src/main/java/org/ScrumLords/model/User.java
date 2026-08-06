package org.ScrumLords.model;

/**
 * Represents a generic user of the Movie Ticket Booking System.
 * Serves as the base class for all user types, such as customers
 * and administrators.
 *
 * @author A. Garcia
 * @version 1.0
 */
public abstract class User {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Constructs a User object.
     *
     * @param userId unique identifier for the user
     * @param username user's account username
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email address
     * @param password user's account password
     */
    public User(String userId, String username, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the user's full name.
     *
     * @return the user's first and last name separated by a space
     */
    public String getFullName() {
        return (firstName + " " + lastName);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}