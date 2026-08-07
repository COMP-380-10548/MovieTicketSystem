package org.ScrumLords;

import org.ScrumLords.model.User;

/**
 * Utility class that holds the authenticated user within an accessible class other controllers and models can access.
 */
public final class SessionManager {
    private static User currentUser;

    private SessionManager() {}

    /**
     * Store instantiated user into session
     * @param user An instantiaed user to add into the session
     */
    public static void store(User user) {
        currentUser = user;
    }

    /**
     * Remove current user from session
     */
    public static void release() {
        currentUser = null;
    }

    /**
     * Obtain the current user stored in the session
     * @return The stored user within the session
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Check whether or not a user is currently stored in the session
     * @return A boolean showing if a user is stored
     */
    public static boolean isStored() {
        return currentUser != null;
    }
}
