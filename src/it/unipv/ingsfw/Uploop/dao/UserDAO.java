package it.unipv.ingsfw.Uploop.dao;

import it.unipv.ingsfw.Uploop.model.User;

public interface UserDAO {
    // Metodo per trovare un utente dato il nome
    User findByUsername(String username);
    
    // Metodo per salvare un nuovo utente
    void save(User user);
}