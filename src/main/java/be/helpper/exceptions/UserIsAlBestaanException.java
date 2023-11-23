package be.helpper.exceptions;

public class UserIsAlBestaanException extends RuntimeException{
    public UserIsAlBestaanException() {
        super("Persoon met deze email is al bestaan");
    }

    public UserIsAlBestaanException(String message) {
        super(message);
    }
}
