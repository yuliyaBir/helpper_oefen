package be.helpper.exceptions;
public class GoedkeuringIsAlToegevoegdException extends RuntimeException{
    public GoedkeuringIsAlToegevoegdException() {
        super("Goedgekeuring is al toegevoegd");
    }
}
