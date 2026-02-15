package com.uploop.model;

// Questa classe usa il pattern BUILDER
public class Locandina {
    // Parametri della locandina (tutti privati)
    private String formato;    // es. A4, A3
    private String tipoCarta;  // es. Lucida, Opaca
    private String testo;      // Il contenuto
    private boolean graficaVettoriale;
    private int quantita;

    // Costruttore PRIVATO: si può usare solo tramite il Builder
    private Locandina(Builder builder) {
        this.formato = builder.formato;
        this.tipoCarta = builder.tipoCarta;
        this.testo = builder.testo;
        this.graficaVettoriale = builder.graficaVettoriale;
        this.quantita = builder.quantita;
    }

    // Metodo per stampare i dettagli (utile per la console)
    @Override
    public String toString() {
        return "Locandina [Formato=" + formato + ", Carta=" + tipoCarta + 
               ", Quantità=" + quantita + ", Vettoriale=" + graficaVettoriale + "]";
    }

    // --- INNER CLASS: IL BUILDER ---
    public static class Builder {
        // Parametri richiesti (obbligatori)
        private String formato;
        private int quantita;

        // Parametri opzionali (con valori di default)
        private String tipoCarta = "Standard";
        private String testo = "";
        private boolean graficaVettoriale = false;

        // Costruttore del Builder (chiede solo i dati obbligatori)
        public Builder(String formato, int quantita) {
            this.formato = formato;
            this.quantita = quantita;
        }

        // Metodi per impostare gli opzionali (ritornano il Builder stesso)
        public Builder setTipoCarta(String tipoCarta) {
            this.tipoCarta = tipoCarta;
            return this;
        }

        public Builder setTesto(String testo) {
            this.testo = testo;
            return this;
        }

        public Builder setGraficaVettoriale(boolean attiva) {
            this.graficaVettoriale = attiva;
            return this;
        }

        // Metodo che crea finalmente l'oggetto vero
        public Locandina build() {
            return new Locandina(this);
        }
    }
}