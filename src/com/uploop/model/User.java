package com.uploop.model;

public class User {
    private String username;
    private String email; // NUOVO CAMPO
    private String password;
    private String ruolo; 

    public User(String username, String email, String password, String ruolo) {
        this.username = username;
        this.email = email; // Salviamo l'email
        this.password = password;
        this.ruolo = ruolo;
    }

    // Getter e Setter
    public String getUsername() { return username; }
    public String getEmail() { return email; } // NUOVO GETTER
    public String getPassword() { return password; } 
    public String getRuolo() { return ruolo; }
}