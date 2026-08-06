package org.ScrumLords;

import org.ScrumLords.model.Admin;
import org.ScrumLords.model.Booking;
import org.ScrumLords.model.Customer;
import org.ScrumLords.model.Manager;
import org.ScrumLords.model.User;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Handles user authentication and registration against the users collection via the DatabaseService.
 * Maps retrieved MongoDB documents to a subclass of the User template for user instantiation.
 */
public class UserService {
    private final MongoCollection<Document> users;

    public UserService() {
        this.users = DatabaseService.getInstance().getCollection("users"); 
    }

    /**
     * Authenticate a user against the DatabaseService singleton
     * @param username Username String to authenticate against
     * @param password Password String to authenticate against
     * @return The User associated with the authentication query. Can return null if the user doesn't exist or if the password doesn't match.
     */
    public User authenticate(String username, String password) {
        Document doc = users.find(eq("username", username)).first();

        if(doc == null)
            return null;

        String storedHash = doc.getString("password");
        if(!BCrypt.checkpw(password, storedHash))
            return null;

        return toUser(doc);
    }
    
    /**
     * Registers a new user against the DatabaseService singleton
     * @param username String that defines the new user's username. Is checked against the database to be unique.
     * @param firstName String that defines the new user's first name.
     * @param lastName String that defines the new user's last name.
     * @param email String that defines the new user's email.
     * @param password String that defines the new user's password.
     * @return Boolean showing if the new user was created successfully.
     */
    public boolean register(String username, String firstName, String lastName, String email, String password) {
        username = username.trim().toLowerCase();
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim().toLowerCase();

        if(users.find(eq("username", username)).first() != null)
            return false;

        // BCrypt only works on 72 bytes (of UTF_8 characters) or less before truncating the String
        if(password.getBytes(StandardCharsets.UTF_8).length > 72)
            return false;

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        Document doc = new Document("username", username)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("email", email)
            .append("password", hashedPassword)
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
