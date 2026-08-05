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

    public User authenticate(String username, String password) {
        Document doc = users.find(eq("username", username)).first();

        if(doc == null)
            return null;
        if(!doc.getString("password").equals(password))
            return null;

        return toUser(doc);
    }
    
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
