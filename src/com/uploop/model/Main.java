package com.uploop.model;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("=== AVVIO SISTEMA UPLOOP ===");

        // --- PARTE 1: LOGIN ---
        SessionManager sessione = SessionManager.getInstance();
        sessione.login("MarioRossi_Designer");
        
        System.out.println("Utente attivo: " + sessione.getUser());
        System.out.println("-----------------------------");

        // --- PARTE 2: CREAZIONE LOCANDINA ---
        System.out.println("Creazione nuovo ordine in corso...");
        
        Locandina ordine1 = new Locandina.Builder("A3", 500)
                .setTipoCarta("Lucida")
                .setGraficaVettoriale(true)
                .setTesto("Concerto Estate 2024")
                .build();

        System.out.println("Ordine creato: " + ordine1);
        System.out.println("-----------------------------");

        // --- PARTE 3: PAGAMENTO (NUOVO!) ---
        System.out.println("Avvio procedura di pagamento...");
        
        // Qui decidiamo di usare PayPal
        PaymentStrategy metodoScelto = new PayPalPayment("mario.rossi@gmail.com");
        
        // Eseguiamo il pagamento
        metodoScelto.paga(150.00);
        
        System.out.println("=== SISTEMA TERMINATO ===");
    }
}