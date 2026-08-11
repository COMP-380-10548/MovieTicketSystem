package org.ScrumLords;

import static com.mongodb.client.model.Filters.eq;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

import org.ScrumLords.model.Admin;
import org.ScrumLords.model.Booking;
import org.ScrumLords.model.Customer;
import org.ScrumLords.model.Manager;
import org.ScrumLords.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

/**
 * Handles user authentication and registration against the users collection via the DatabaseService.
 * Maps retrieved MongoDB documents to a subclass of the User template for user instantiation.
 *
 * @author C. Wichman
 * @version 1.0
 * @since 2026-08-05
 */
public class UserService {
    // Reference https://regexr.com/
    // Patterns ensure fields are in a valid alphanumeric format
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-z0-9_.-]+");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-z0-9_.-]+@[a-z0-9.-]+.[a-z]+");

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
     * @return An instantiated User created after registration. Used to explicitly verify the new user's information. 
     */
    public User register(String username, String firstName, String lastName, String email, String password) {
        username = username.trim().toLowerCase();
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim().toLowerCase();

        if(!(verifyUsername(username) && verifyNames(firstName, lastName) && verifyEmail(email) && verifyPassword(password))) {
            return null;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        Document doc = new Document("username", username)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("email", email)
            .append("password", hashedPassword)
            .append("role", "customer")
            .append("bookingHistory", List.of());
        
        users.insertOne(doc);
        return toUser(doc);
    }

    /**
     * Updates a user's modifiyable information.
     * @param userId The userId String of the desired user to update.
     * @param firstName A first name String that the user amends to.
     * @param lastName A last name String that the user amends to.
     * @param email An email String that the user amends to.
     * @return An instantiated User updated with new profile information.
     */
    public User updateProfile(String userId, String firstName, String lastName, String email) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim().toLowerCase();

        if(!(verifyNames(firstName, lastName) && verifyEmail(email)))
            return null;

        Document filter = new Document("_id", new ObjectId(userId));
        Document update = new Document("$set", new Document("firstName", firstName)
            .append("lastName", lastName)
            .append("email", email)
        );

        UpdateResult result = users.updateOne(filter, update);
        if(result.getMatchedCount() == 0)
            return null;

        Document doc = users.find(filter).first();
        return toUser(doc);
    }

    public boolean deleteUser(String userId) {
        Document filter = new Document("_id", new ObjectId(userId));
        Document update = new Document("$set", new Document("deleted", true)
            .append("username", "deleted_" + userId)
            .append("password", "")
        );

        UpdateResult result = users.updateOne(filter, update);
        return result.getMatchedCount() > 0;
    }

    /**
     * Check if the username is formatted properly.
     * @param username A username String to verify against.
     * @return A boolean of the username's validity.
     */
    private boolean verifyUsername(String username) {
        // Check if username matches regex pattern
        if(!USERNAME_PATTERN.matcher(username).matches())
            return false;

        // Check if username already exists
        if(users.find(eq("username", username)).first() != null)
            return false;

        return true;
    }

    /**
     * Check if the first and last names are formatted properly.
     * @param firstName A first name String to verify against.
     * @param lastName A last name String to verify against.
     * @return A boolean of the names' validity.
     */
    private boolean verifyNames(String firstName, String lastName) {
        return !firstName.isEmpty() && !lastName.isEmpty();
    }

    /**
     * Check if the email is formatted properly.
     * @param email An email String to verify against.
     * @return A boolean of the email's validity.
     */
    private boolean verifyEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Check if the password is formatted properly
     * @param password A plaintext password String to verify against.
     * @return A boolean of the password's validity.
     */
    private boolean verifyPassword(String password) {
        // Check if password is in ASCII format
        if(!StandardCharsets.US_ASCII.newEncoder().canEncode(password))
            return false;

        // BCrypt only works on 72 bytes or less before truncating the String
        // Each ASCII character is 1 byte so max length is 72
        if(password.length() > 72)
            return false;
        
        return true;
    }

    /**
     * Convert a MongoDB Document into an appropriate instantiated User class.
     * @param doc The MongoDB document to base the new User object off of.
     * @return An instantiated implementation of a User object. Will be either a Customer, Manager, or Admin based on the user's role.
     */
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
