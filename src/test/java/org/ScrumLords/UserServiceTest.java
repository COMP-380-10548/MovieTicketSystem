package org.ScrumLords;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.ScrumLords.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("authenticate with correct credentials")
    void authenticate_correct() {
        String username = "testuser" + UUID.randomUUID().toString().substring(0,8);
        userService.register(username, "test", "user", username + "@example.com", "password123");

        User result = userService.authenticate(username, "password123");

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

}
