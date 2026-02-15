package com.uploop.model;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDAO implements UserDAO {
    // Questo simula la tabella del database
    private Map<String, User> database = new HashMap<>();

    public InMemoryUserDAO() {
        // Pre-carichiamo alcuni utenti per fare i test
        database.put("mario", new User("mario", "password123", "Cliente"));
        database.put("luigi", new User("luigi", "design2024", "Designer"));
    }

    @Override
    public User findByUsername(String username) {
        return database.get(username);
    }

    @Override
    public void save(User user) {
        database.put(user.getUsername(), user);
        System.out.println("Utente salvato nel DB: " + user.getUsername());
    }
}