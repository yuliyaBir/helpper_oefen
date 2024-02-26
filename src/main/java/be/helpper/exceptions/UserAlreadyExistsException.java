package be.helpper.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("Persoon met deze email is al bestaan");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
