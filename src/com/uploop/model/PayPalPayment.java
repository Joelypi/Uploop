package com.uploop.model;

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