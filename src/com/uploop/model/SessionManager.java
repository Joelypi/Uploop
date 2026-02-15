package com.uploop.model;

public class SessionManager {
    // 1. L'unica istanza statica della classe (Singleton)
    private static SessionManager instance;
    private String currentUser; 

    // 2. Costruttore PRIVATO: nessuno da fuori può fare "new SessionManager()"
    private SessionManager() {}

    // 3. Metodo pubblico per ottenere l'istanza
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Metodi per gestire l'utente
    public void login(String user) {
        this.currentUser = user;
        System.out.println("Utente loggato: " + user);
    }

    public String getUser() {
        return currentUser;
    }
}