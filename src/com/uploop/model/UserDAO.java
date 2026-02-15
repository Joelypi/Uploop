package com.uploop.model;

public interface UserDAO {
    // Metodo per trovare un utente dato il nome
    User findByUsername(String username);
    
    // Metodo per salvare un nuovo utente
    void save(User user);
}