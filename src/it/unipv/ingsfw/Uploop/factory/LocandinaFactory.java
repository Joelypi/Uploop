package it.unipv.ingsfw.Uploop.factory;

import it.unipv.ingsfw.Uploop.model.Locandina;

public class LocandinaFactory {
    
    public static Locandina creaLocandina(String formato, int quantita, String tipoCarta, String testo, boolean graficaVettoriale) {
        
        if (tipoCarta == null || tipoCarta.isEmpty()) {
            tipoCarta = "Standard";
        }
        if (testo == null) {
            testo = "";
        }
        
        // L'ordine qui DEVE corrispondere al costruttore di Locandina
        return new Locandina(formato, tipoCarta, testo, graficaVettoriale, quantita);
    }
}