package be.helpper.rollen;

public class UserHeeftDezeRolAlException extends RuntimeException{
    public UserHeeftDezeRolAlException() {
        super("User heeft deze rol al.");
    }
}
