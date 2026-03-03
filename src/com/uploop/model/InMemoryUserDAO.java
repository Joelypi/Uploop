package com.uploop.model;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDAO implements UserDAO {
    private Map<String, User> database = new HashMap<>();

    public InMemoryUserDAO() {
        // Ora inseriamo anche le email: mario@email.com e luigi@email.com
        database.put("mario", new User("mario", "mario@email.com", "password123", "Cliente"));
        database.put("luigi", new User("luigi", "luigi@email.com", "design2024", "Designer"));
    }

    @Override
    public User findByUsername(String username) {
        return database.get(username);
    }

    @Override
    public void save(User user) {
        database.put(user.getUsername(), user);
        System.out.println("Utente salvato nel DB: " + user.getUsername() + " con email: " + user.getEmail());
    }
}