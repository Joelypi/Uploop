package com.uploop.model;

public class User {
    private String username;
    private String password;
    private String ruolo; // Es. "Designer" o "Cliente"

    public User(String username, String password, String ruolo) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    // Getter e Setter
    public String getUsername() { return username; }
    public String getPassword() { return password; } // In un caso reale andrebbe criptata!
    public String getRuolo() { return ruolo; }
}