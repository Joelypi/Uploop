package it.unipv.ingsfw.Uploop.payment;

public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void paga(double importo) {
        System.out.println(">> Pagamento di " + importo + " euro effettuato con PAYPAL (" + email + ")");
    
    } 
}
