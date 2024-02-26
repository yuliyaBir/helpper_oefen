package be.helpper.exceptions;
public class GoedkeuringIsAlreadyAddedException extends RuntimeException{
    public GoedkeuringIsAlreadyAddedException() {
        super("Goedgekeuring is al toegevoegd");
    }
}
