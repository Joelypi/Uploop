package it.unipv.ingsfw.Uploop.controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unipv.ingsfw.Uploop.dao.MySqlOrderDAO;
import it.unipv.ingsfw.Uploop.factory.LocandinaFactory;
import it.unipv.ingsfw.Uploop.frame.DashboardFrame;
import it.unipv.ingsfw.Uploop.model.Locandina;
import it.unipv.ingsfw.Uploop.model.Order;
import it.unipv.ingsfw.Uploop.model.SessionManager;
import it.unipv.ingsfw.Uploop.model.User;
import it.unipv.ingsfw.Uploop.payment.CreditCardPayment;
import it.unipv.ingsfw.Uploop.payment.PayPalPayment;
import it.unipv.ingsfw.Uploop.payment.PaymentStrategy;

public class OrderController {
    
    // Il controller riceve i dati puliti dalla View e si occupa della logica di business
    // "metodoPagamento" vale "CARTA" oppure "PAYPAL"; "datiPagamento" è il numero carta o l'email a seconda del metodo
    public void confermaOrdine(String formato, int quantita, String carta, boolean vettoriale, String note, double totale, String metodoPagamento, String datiPagamento, JDialog paymentDialog, JFrame orderFrame) {
        
        User utenteCorrente = SessionManager.getInstance().getCurrentUser();
        
        if (utenteCorrente != null) {
            try {
                // 1. Pattern Factory: Creazione oggetto Locandina
                Locandina locandina = LocandinaFactory.creaLocandina(formato, quantita, carta, note, vettoriale);
                
                // 2. Pattern Strategy: la scelta della strategia dipende da cosa ha selezionato l'utente
                PaymentStrategy strategy;
                if ("PAYPAL".equals(metodoPagamento)) {
                    strategy = new PayPalPayment(datiPagamento);
                } else {
                    strategy = new CreditCardPayment(datiPagamento);
                }
                strategy.paga(totale);
                
                // 3. Pattern DAO: Salvataggio nel database MySQL
                MySqlOrderDAO orderDAO = new MySqlOrderDAO();
                orderDAO.salvaOrdine(utenteCorrente.getUsername(), formato, quantita, carta, vettoriale, note);
                
                // 4. Aggiornamento del Model in memoria
                Order nuovoOrdine = new Order(formato, quantita, carta, totale);
                utenteCorrente.aggiungiOrdine(nuovoOrdine);
                SessionManager.getInstance().salvaDati();
                
                // 5. Chiusura delle View e feedback utente
                JOptionPane.showMessageDialog(paymentDialog, "Pagamento completato!\nTroverai l'ordine nella tua Area Personale.", "Ordine Confermato", JOptionPane.INFORMATION_MESSAGE); 
                paymentDialog.dispose();
                orderFrame.dispose();
                
                // Ritorna alla Dashboard
                new DashboardFrame();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(paymentDialog, "Errore durante l'elaborazione dell'ordine: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}