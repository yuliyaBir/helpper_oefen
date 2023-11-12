package be.helpper.users;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;

import static jakarta.ws.rs.core.Response.*;

public class UserIsNietGevondenException extends RuntimeException {
    public UserIsNietGevondenException() {
        super("User is niet gevonden.");
    }
}
