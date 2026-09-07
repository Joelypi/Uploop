package it.unipv.ingsfw.Uploop.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String messaggio) {
        super(messaggio);
    }
}