package it.unipv.ingsfw.Uploop.payment;

public class CreditCardPayment implements PaymentStrategy {
    private String numeroCarta;

    public CreditCardPayment(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    @Override
    public void paga(double importo) {
        System.out.println(">> Pagamento di " + importo + " euro effettuato con CARTA (" + numeroCarta + ")");
    }
}