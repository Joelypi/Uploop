package com.uploop.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {

    private final Color PRIMARY_COLOR = new Color(0, 0, 200);

    public DashboardFrame() {
        setTitle("Uploop - Dashboard");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        setContentPane(mainPanel);

        // 1. LOGO E BENVENUTO
        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        Image img = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(img));
        mainPanel.add(lblLogo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        User utente = SessionManager.getInstance().getCurrentUser();
        String nome = (utente != null) ? utente.getUsername() : "Utente";
        JLabel lblWelcome = new JLabel("Benvenuto, " + nome, SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        lblWelcome.setForeground(PRIMARY_COLOR);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblWelcome);
        
        JLabel lblRuolo = new JLabel((utente != null) ? utente.getRuolo() : "", SwingConstants.CENTER);
        lblRuolo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblRuolo.setForeground(Color.GRAY);
        lblRuolo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblRuolo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // 2. BOTTONI DEL MENU
        mainPanel.add(createMenuButton("CREA NUOVA LOCANDINA", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderFrame();
            }
        }));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnStorico = createMenuButton("VISUALIZZA STORICO", null);
        btnStorico.setBackground(new Color(240, 240, 240)); // Grigio chiaro
        btnStorico.setForeground(Color.BLACK);
        btnStorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Storico Ordini:\n- 100x A4 (Pagato)\n- 500x A3 (In lavorazione)", "I miei ordini", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainPanel.add(btnStorico);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // 3. BOTTONE LOGOUT
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 14));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionManager.getInstance().logout();
                new LoginFrame();
                dispose();
            }
        });
        mainPanel.add(btnLogout);

        setVisible(true);
    }

    // Metodo helper per creare bottoni con lo stesso stile
    private JButton createMenuButton(String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(PRIMARY_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(400, 60));
        if (action != null) btn.addActionListener(action);
        return btn;
    }
}