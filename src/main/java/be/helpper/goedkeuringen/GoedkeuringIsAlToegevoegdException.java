package be.helpper.goedkeuringen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GoedkeuringIsAlToegevoegdException extends RuntimeException{
    public GoedkeuringIsAlToegevoegdException() {
        super("Goedgekeuring is al toegevoegd");
    }
}
