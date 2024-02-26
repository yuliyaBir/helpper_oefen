package be.helpper.exceptions;

public class UserIsNotFoundException extends RuntimeException {
    public UserIsNotFoundException() {
        super("User is niet gevonden.");
    }
}
