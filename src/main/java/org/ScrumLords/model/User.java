package org.ScrumLords.model;

public abstract class User {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

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