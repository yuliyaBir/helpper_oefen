package be.helpper.users;

public class UserIsNietGevondenException extends RuntimeException {
    public UserIsNietGevondenException() {
        super("User is niet gevonden.");
    }
}
