package com.uploop.model;

public class SessionManager {
    private static SessionManager instance;
    private User currentUser; 
    private UserDAO userDAO;

    private SessionManager() {
        this.userDAO = new InMemoryUserDAO();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        User userTrovato = userDAO.findByUsername(username);

        if (userTrovato != null && userTrovato.getPassword().equals(password)) {
            this.currentUser = userTrovato;
            return true;
        } else {
            return false;
        }
    }

    // --- ECCO IL PEZZO CHE MANCAVA ---
    public void logout() {
        this.currentUser = null;
        System.out.println("Utente disconnesso.");
    }
    // ---------------------------------

    public User getCurrentUser() {
        return currentUser;
    }
}