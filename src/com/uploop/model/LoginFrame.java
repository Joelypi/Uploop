package com.uploop.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    
    private JTextField userField;
    private JPasswordField passField;
    // Colore blu scuro professionale (simile al tuo logo)
    private final Color PRIMARY_COLOR = new Color(0, 51, 153);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public LoginFrame() {
        // Titolo della finestra (quello sulla barra in alto)
        setTitle("Uploop - Login");
        setSize(400, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Pannello principale bianco
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 45, 30, 45));
        setContentPane(mainPanel);

        // 1. LOGO
        try {
            JLabel lblLogo = new JLabel();
            lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Carica logo.png dalla cartella src
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
            Image img = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
            mainPanel.add(lblLogo);
        } catch (Exception e) {
            System.out.println("Logo non trovato, procedo senza immagine.");
        }

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 2. TITOLO RICHIESTO: "Login Uploop"
        JLabel lblTitle = new JLabel("Login Uploop");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 3. CAMPI DI INPUT
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        formPanel.setBackground(Color.WHITE);
        formPanel.setMaximumSize(new Dimension(300, 150));

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        userField = new JTextField();
        userField.setFont(MAIN_FONT);
        
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passField = new JPasswordField();
        passField.setFont(MAIN_FONT);

        formPanel.add(lblUser);
        formPanel.add(userField);
        formPanel.add(lblPass);
        formPanel.add(passField);
        mainPanel.add(formPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 4. BOTTONE ACCEDI
        JButton btnLogin = new JButton("ACCEDI");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(300, 45));
        
        // Per rendere il colore visibile su Mac/Windows
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eseguiLogin();
            }
        });
        mainPanel.add(btnLogin);
        
        setVisible(true);
    }

    private void eseguiLogin() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        
        // Chiama il SessionManager per validare le credenziali
        boolean loggato = SessionManager.getInstance().login(user, pass);

        if (loggato) {
            new DashboardFrame(); // Apre la Dashboard se i dati sono corretti
            dispose(); // Chiude la finestra di Login
        } else {
            JOptionPane.showMessageDialog(this, "Username o Password non corretti", "Errore di accesso", JOptionPane.ERROR_MESSAGE);
        }
    }
}