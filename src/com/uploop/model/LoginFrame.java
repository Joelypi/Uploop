package com.uploop.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    
    private JTextField userField;
    private JPasswordField passField;
    private final Color PRIMARY_COLOR = new Color(0, 51, 153);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 16); // Font un po' più grande

    public LoginFrame() {
        setTitle("Uploop - Login");
        // FINESTRA PIÙ GRANDE (era 400x520, ora è 500x600)
        setSize(500, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Pannello principale BIANCO PURO (il logo si fonderà qui!)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE); 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(50, 60, 50, 60)); // Più margine ai lati
        setContentPane(mainPanel);

        // 1. LOGO
        try {
            JLabel lblLogo = new JLabel();
            lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
            // Logo leggermente più grande
            Image img = logoIcon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
            mainPanel.add(lblLogo);
        } catch (Exception e) {
            System.out.println("Logo non trovato.");
        }

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // 2. TITOLO
        JLabel lblTitle = new JLabel("Login Uploop");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Titolo più grande
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // 3. CAMPI DI INPUT
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 5, 10));
        formPanel.setBackground(Color.WHITE); // Sfondo bianco anche qui
        formPanel.setMaximumSize(new Dimension(350, 180));

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userField = new JTextField();
        userField.setFont(MAIN_FONT);
        
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passField = new JPasswordField();
        passField.setFont(MAIN_FONT);

        formPanel.add(lblUser);
        formPanel.add(userField);
        formPanel.add(lblPass);
        formPanel.add(passField);
        mainPanel.add(formPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // 4. BOTTONE ACCEDI
        JButton btnLogin = new JButton("ACCEDI");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(350, 50)); // Bottone più grande
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eseguiLogin();
            }
        });
        mainPanel.add(btnLogin);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 5. BOTTONE REGISTRATI
        JButton btnGoRegister = new JButton("Non hai un account? Registrati");
        btnGoRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGoRegister.setForeground(PRIMARY_COLOR);
        btnGoRegister.setContentAreaFilled(false); 
        btnGoRegister.setBorderPainted(false);
        btnGoRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGoRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnGoRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame(); 
                dispose(); 
            }
        });
        mainPanel.add(btnGoRegister);

        setVisible(true);
    }

    private void eseguiLogin() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        
        boolean loggato = SessionManager.getInstance().login(user, pass);

        if (loggato) {
            new DashboardFrame(); 
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Username o Password non corretti", "Errore di accesso", JOptionPane.ERROR_MESSAGE);
        }
    }
}