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

    // --- ORA IL METODO CHIEDE 4 DATI (INCLUSA L'EMAIL) ---
    public boolean register(String username, String email, String password, String ruolo) {
        // Controllo se l'utente esiste già
        if (userDAO.findByUsername(username) != null) {
            return false; // Utente già esistente
        }
        
        // Crea e salva il nuovo utente passando tutti e 4 i parametri
        User newUser = new User(username, email, password, ruolo);
        userDAO.save(newUser);
        return true;
    }

    public void logout() {
        this.currentUser = null;
        System.out.println("Utente disconnesso.");
    }

    public User getCurrentUser() {
        return currentUser;
    }
}