package be.helpper.exceptions;

public class UserIsNietGevondenException extends RuntimeException {
    public UserIsNietGevondenException() {
        super("User is niet gevonden.");
    }
}
