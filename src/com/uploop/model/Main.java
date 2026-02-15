package com.uploop.model;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Questo comando avvia l'interfaccia grafica in modo sicuro
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // APRI LA FINESTRA DI LOGIN
                new LoginFrame(); 
            }
        });
    }
}