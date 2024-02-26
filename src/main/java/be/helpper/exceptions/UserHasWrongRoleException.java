package be.helpper.exceptions;

public class UserHasWrongRoleException extends RuntimeException{
    public UserHasWrongRoleException(String message) {
        super(message);
    }
}
