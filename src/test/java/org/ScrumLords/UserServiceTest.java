package org.ScrumLords;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.ScrumLords.model.Customer;
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
     * Create a unique username based on java UUIDs for the test user
     * @return A username String in the format of "testuser.[8 character UUID]"
     */
    private String uniqueUsername() {
        return "testuser." + UUID.randomUUID().toString().substring(0,8);
    }

    /**
     * Fully delete the temporary test user document after each test 
     */
    @AfterEach
    void cleanup() {
        if(testUserId != null) {
            users.deleteOne(eq("_id", new ObjectId(testUserId)));
        }
    }

    @Test
    @DisplayName("authenticate with correct credentials")
    void authenticate_correct() {
        String username = uniqueUsername();
        User testUser = userService.register(username, "test", "user", username + "@example.com", "password123");
        testUserId = testUser.getUserId();

        User result = userService.authenticate(username, "password123");

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    @DisplayName("authenticate with wrong password")
    void authenticate_wrong_username() {
        String username = uniqueUsername();
        User testUser = userService.register(username, "test", "user", username + "@example.com", "password123");
        testUserId = testUser.getUserId();

        User result = userService.authenticate(username, "wrongpassword");

        assertNull(result);
    }

    @Test
    @DisplayName("authenticate with unknown username")
    void authenticate_unknown_username() {
        // Create an invalid username to authenticate against
        String username = "nouser." + UUID.randomUUID().toString().substring(0,8);

        User result = userService.authenticate(username, "password123");

        assertNull(result);
    }

    @Test
    @DisplayName("authenticate to deleted user")
    void authenticate_deleted_user() {
        String username = uniqueUsername();
        User testUser = userService.register(username, "test", "user", username + "@example.com", "password123");
        testUserId = testUser.getUserId();
        userService.deleteUser(testUserId);

        User result = userService.authenticate("_deleted" + testUserId, "");

        assertNull(result);
    }

    @Test
    @DisplayName("deleteUser on a valid user")
    void deleteUser_correct() {
        String username = uniqueUsername();
        User testUser = userService.register(username, "test", "user", username + "@example.com", "password123");
        testUserId = testUser.getUserId();

        boolean result = userService.deleteUser(testUserId);

        assertTrue(result);

        Document doc = users.find(eq("_id", new ObjectId(testUserId))).first();
        assertNotNull(doc);
        assertTrue(doc.getBoolean("deleted"));
        assertEquals("deleted_" + testUserId, doc.getString("username"));
        assertEquals("", doc.getString("password"));
    }

    @Test
    @DisplayName("deleteUser on a non-existant user")
    void deleteUser_invalid_user() {
        String randomUserId = new ObjectId().toString();

        boolean result = userService.deleteUser(randomUserId);

        assertFalse(result);
    }

    @Test
    @DisplayName("deleteUser on a invalid userId")
    void deleteUser_invalid_userId() {
        boolean result = userService.deleteUser("invalid-user-id");

        assertFalse(result);
    }
}
