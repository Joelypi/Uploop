package it.unipv.ingsfw.Uploop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Ora implementa Serializable, permettendo al SessionManager di salvare su file!
public class User implements Serializable {
    private String username;
    private String password;
    private List<Order> ordiniPersonali;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ordiniPersonali = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    // Aggiunto il metodo per leggere la password necessario al login
    public String getPassword() {
        return password;
    }

    public List<Order> getOrdini() {
        return ordiniPersonali;
    }

    public void aggiungiOrdine(Order nuovoOrdine) {
        ordiniPersonali.add(nuovoOrdine);
    }
}