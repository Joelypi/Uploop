package com.uploop.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFrame extends JFrame {
    
    private JTextField userField;
    private JTextField emailField; 
    private JPasswordField passField;
    private JComboBox<String> comboRuolo;
    private final Color PRIMARY_COLOR = new Color(0, 51, 153);

    public RegistrationFrame() {
        setTitle("Uploop - Nuova Registrazione");
        setSize(400, 550); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // --- SFONDO PNG ---
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    java.net.URL imgUrl = getClass().getResource("/sfondo.png");
                    if (imgUrl != null) {
                        Image bgImage = new ImageIcon(imgUrl).getImage();
                        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                    }
                } catch (Exception e) {
                    System.out.println("Sfondo PNG non trovato.");
                }
            }
        };

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 45, 30, 45));
        setContentPane(mainPanel);

        // TITOLO
        JLabel lblTitle = new JLabel("Crea Account");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // CAMPI DI INPUT (Trasparenti)
        JPanel formPanel = new JPanel(new GridLayout(8, 1, 5, 5));
        formPanel.setOpaque(false); // TRASPARENTE
        formPanel.setMaximumSize(new Dimension(300, 250));

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        formPanel.add(lblUser);
        userField = new JTextField();
        formPanel.add(userField);

        JLabel lblEmail = new JLabel("Indirizzo Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
        formPanel.add(lblEmail);
        emailField = new JTextField();
        formPanel.add(emailField);
        
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        formPanel.add(lblPass);
        passField = new JPasswordField();
        formPanel.add(passField);

        JLabel lblRuolo = new JLabel("Ruolo:");
        lblRuolo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        formPanel.add(lblRuolo);
        String[] ruoli = {"Cliente", "Designer"};
        comboRuolo = new JComboBox<>(ruoli);
        formPanel.add(comboRuolo);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // BOTTONE REGISTRATI
        JButton btnRegister = new JButton("CONFERMA REGISTRAZIONE");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBackground(PRIMARY_COLOR);
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setOpaque(true);
        btnRegister.setBorderPainted(false);
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(300, 45));
        
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registraUtente();
            }
        });
        mainPanel.add(btnRegister);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // BOTTONE TORNA INDIETRO
        JButton btnBack = new JButton("Torna al Login");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBack.setForeground(PRIMARY_COLOR);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        mainPanel.add(btnBack);

        setVisible(true);
    }

    private void registraUtente() {
        String user = userField.getText();
        String email = emailField.getText(); 
        String pass = new String(passField.getPassword());
        String ruolo = (String) comboRuolo.getSelectedItem();

        if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Compila tutti i campi (inclusa l'email)!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean registrato = SessionManager.getInstance().register(user, email, pass, ruolo);

        if (registrato) {
            JOptionPane.showMessageDialog(this, "Registrazione completata! Ora puoi fare il login.");
            new LoginFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username già esistente. Scegline un altro.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}