package it.unipv.ingsfw.Uploop.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String messaggio) {
        super(messaggio);
    }
}