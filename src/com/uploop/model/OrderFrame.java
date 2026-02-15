package com.uploop.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderFrame extends JFrame {

    private JComboBox<String> comboFormato;
    private JComboBox<String> comboCarta;
    private JTextField txtQuantita;
    private JCheckBox chkVettoriale;
    private JTextArea txtNote;
    private final Color PRIMARY_COLOR = new Color(0, 0, 200);
    private final Font LABEL_FONT = new Font("Arial", Font.BOLD, 14);

    public OrderFrame() {
        setTitle("Nuovo Ordine");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // 1. INTESTAZIONE CON LOGO
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        Image img = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(img));
        headerPanel.add(lblLogo);
        
        JLabel lblTitle = new JLabel("Configura la tua Locandina");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(lblTitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // 2. FORM CENTRALE
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(0, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Riga 1: Formato
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        JLabel lblFormato = new JLabel("Formato:", SwingConstants.RIGHT);
        lblFormato.setFont(LABEL_FONT);
        formPanel.add(lblFormato, gbc);
        
        gbc.gridx = 1; gbc.weightx = 0.7;
        String[] formati = {"A4 (Standard)", "A3 (Doppio)", "A2 (Poster)", "Gigante 6x3"};
        comboFormato = new JComboBox<>(formati);
        formPanel.add(comboFormato, gbc);

        // Riga 2: Quantità
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        JLabel lblQta = new JLabel("Quantità:", SwingConstants.RIGHT);
        lblQta.setFont(LABEL_FONT);
        formPanel.add(lblQta, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        txtQuantita = new JTextField("100");
        formPanel.add(txtQuantita, gbc);

        // Riga 3: Carta
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        JLabel lblCarta = new JLabel("Carta:", SwingConstants.RIGHT);
        lblCarta.setFont(LABEL_FONT);
        formPanel.add(lblCarta, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        String[] carte = {"Standard (80gr)", "Lucida (130gr)", "Opaca Premium", "Cartoncino"};
        comboCarta = new JComboBox<>(carte);
        formPanel.add(comboCarta, gbc);

        // Riga 4: Opzioni
        gbc.gridx = 1; gbc.gridy = 3;
        chkVettoriale = new JCheckBox("Richiesta Grafica Vettoriale (+20€)");
        chkVettoriale.setBackground(Color.WHITE);
        chkVettoriale.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(chkVettoriale, gbc);

        // Riga 5: Note
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.NORTH;
        JLabel lblNote = new JLabel("Note:", SwingConstants.RIGHT);
        lblNote.setFont(LABEL_FONT);
        formPanel.add(lblNote, gbc);

        gbc.gridx = 1; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        txtNote = new JTextArea(5, 20);
        txtNote.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        formPanel.add(new JScrollPane(txtNote), gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 3. BOTTONE IN BASSO
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 30, 0));
        
        JButton btnPaga = new JButton("CALCOLA TOTALE E PAGA");
        btnPaga.setFont(new Font("Arial", Font.BOLD, 16));
        btnPaga.setBackground(PRIMARY_COLOR);
        btnPaga.setForeground(Color.WHITE);
        btnPaga.setFocusPainted(false);
        btnPaga.setBorderPainted(false);
        btnPaga.setOpaque(true);
        btnPaga.setPreferredSize(new Dimension(300, 50));
        
        btnPaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                concludiOrdine();
            }
        });
        bottomPanel.add(btnPaga);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // (Il metodo concludiOrdine rimane uguale a prima, lo ometto per brevità ma nel tuo file ci sarà)
    private void concludiOrdine() {
        try {
            String formato = (String) comboFormato.getSelectedItem();
            int quantita = Integer.parseInt(txtQuantita.getText());
            String carta = (String) comboCarta.getSelectedItem();
            boolean vettoriale = chkVettoriale.isSelected();

            Locandina locandina = new Locandina.Builder(formato, quantita)
                    .setTipoCarta(carta)
                    .setGraficaVettoriale(vettoriale)
                    .setTesto(txtNote.getText())
                    .build();

            double prezzo = quantita * 0.50; 
            if (vettoriale) prezzo += 20.0;

            String[] opzioni = {"PayPal", "Carta di Credito"};
            int scelta = JOptionPane.showOptionDialog(this, 
                    "Totale Ordine: " + prezzo + "€.\nScegli metodo di pagamento:", 
                    "Checkout", 
                    0, JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);

            PaymentStrategy strategy;
            if (scelta == 0) {
                strategy = new PayPalPayment("user@email.com");
            } else {
                strategy = new CreditCardPayment("1234-5678-9012");
            }
            strategy.paga(prezzo);
            JOptionPane.showMessageDialog(this, "Pagamento Riuscito! Ordine inviato.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Inserisci una quantità valida!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}