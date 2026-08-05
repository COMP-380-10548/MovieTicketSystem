package org.ScrumLords;

import org.ScrumLords.model.Admin;
import org.ScrumLords.model.Booking;
import org.ScrumLords.model.Customer;
import org.ScrumLords.model.Manager;
import org.ScrumLords.model.User;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

import java.util.List;

public class UserService {
    private final MongoCollection<Document> users;

    public UserService() {
        this.users = DatabaseService.getInstance().getCollection("users"); 
    }

    /**
     * Authenticate a user against the DatabaseService singleton
     * @param username A String of the username to authenticate against
     * @param password A string of the password to authenticate against
     * @return Returns a registered user with the user's queried information. Can return null if the user doesn't exist or the password doesn't match.
     */
    public User authenticate(String username, String password) {
        Document doc = users.find(eq("username", username)).first();

        if(doc == null) {
            System.out.println("Document doesn't exist! D:");
            return null;
        }
        if(!doc.getString("password").equals(password))
            return null;

        return toUser(doc);
    }
    
    /**
     * Registeres a new user against the DatabaseService singleton
     * @param username A string that defines the user's username. Is checked against the database to be unique.
     * @param firstName A string that defines the user's first name.
     * @param lastName A string that defines the user's last name.
     * @param email A string that defines the user's email.
     * @param password A string that defines the user's password.
     * @return A boolean representing whether or not the user was created successfully.
     */
    public boolean register(String username, String firstName, String lastName, String email, String password) {
        if(users.find(eq("username", username)).first() != null)
            return false;

        Document doc = new Document("username", username)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("email", email)
            .append("role", "customer")
            .append("bookingHistory", List.of());
        
        users.insertOne(doc);
        return true;
    }

    private User toUser(Document doc) {
        String userId = doc.getObjectId("_id").toString();
        String username = doc.getString("username");
        String firstName = doc.getString("firstName");
        String lastName = doc.getString("lastName");
        String email = doc.getString("email");
        String password = doc.getString("password");
        String role = doc.getString("role");

        switch(role) {
            case "admin":
                return new Admin(userId, username, firstName, lastName, email, password);
            case "manager":
                return new Manager(userId, username, firstName, lastName, email, password);
            default:
                List<Booking> bookingList = doc.getList("bookingHistory", Booking.class);
                return new Customer(userId, username, firstName, lastName, email, password, bookingList);
        }
    }
}
