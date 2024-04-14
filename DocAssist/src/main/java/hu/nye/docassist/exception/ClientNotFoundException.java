package hu.nye.docassist.exception;

public class ClientNotFoundException extends Exception {
    // Konstruktor, ami egy sztring üzenetet fogad
    public ClientNotFoundException(String message) {
        super(message);
    }
}
