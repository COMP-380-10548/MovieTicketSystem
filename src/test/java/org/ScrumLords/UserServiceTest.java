package org.ScrumLords;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.ScrumLords.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoCollection;

public class UserServiceTest {

    private final UserService userService = new UserService();
    private final MongoCollection<Document> users = DatabaseService.getInstance().getCollection("users");

    private String testUserId;

    /**
     * Fully delete the temporary test user document after each test 
     */
    @AfterEach
    void cleanup() {
        if(testUserId != null) {
            users.deleteOne(eq("_id", new ObjectId(testUserId)));
        }
    }

    /**
     * Test case for user authentication being valid
     */
    @Test
    @DisplayName("authenticate with correct credentials")
    void authenticate_correct() {
        // Create a temporary test user with a randomized name
        String username = "testuser." + UUID.randomUUID().toString().substring(0,8);
        User testUser = userService.register(username, "test", "user", username + "@example.com", "password123");
        testUserId = testUser.getUserId();

        User result = userService.authenticate(username, "password123");

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    /**
     * Test case for user authentication failing due to a password mismatch
     */
    @Test
    @DisplayName("authenticate with wrong password")
    void authenticate_wrong_username() {
        // Create a temporary test user with a randomized name
        String username = "testuser." + UUID.randomUUID().toString().substring(0,8);
        User testUser = userService.register(username, "test", "user", username + "@example.com", "password123");
        testUserId = testUser.getUserId();

        User result = userService.authenticate(username, "wrongpassword");

        assertNull(result);
    }

    /**
     * Test case for user authentication failing due to the username not existing
     */
    @Test
    @DisplayName("authenticate with unknown username")
    void authenticate_unknown_username() {
        // Create a temporary test user with a randomized name
        String username = "nouser." + UUID.randomUUID().toString().substring(0,8);

        User result = userService.authenticate(username, "password123");

        assertNull(result);
    }
}
