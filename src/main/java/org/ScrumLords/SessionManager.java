package org.ScrumLords;

import org.ScrumLords.model.User;

public class SessionManager {
    private static User currentUser;

    private SessionManager() {}

    public static void store(User user) {
        currentUser = user;
    }

    public static void release() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isStored() {
        return currentUser != null;
    }
}
