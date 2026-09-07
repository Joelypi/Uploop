package it.unipv.ingsfw.Uploop.model;

public class Locandina {
    private String formato;
    private String tipoCarta;
    private String testo;
    private boolean graficaVettoriale;
    private int quantita;

    // Costruttore con l'ordine esatto dei parametri
    public Locandina(String formato, String tipoCarta, String testo, boolean graficaVettoriale, int quantita) {
        this.formato = formato;
        this.tipoCarta = tipoCarta;
        this.testo = testo;
        this.graficaVettoriale = graficaVettoriale;
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Locandina [Formato=" + formato + ", Carta=" + tipoCarta + 
               ", Quantità=" + quantita + ", Vettoriale=" + graficaVettoriale + "]";
    }
}