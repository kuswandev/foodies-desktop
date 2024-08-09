package com.me.foodies.utils;

import com.me.foodies.models.User;

public class Session {
    private static Session instance;
    private User user;

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void clearSession() {
        this.user = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
