package be.helpper.dto;

import be.helpper.goedkeuringen.Goedkeuring;
import be.helpper.prestaties.Prestatie;

public record PrestatieMetAssistentNaam(long id, String naam, String omschrijving, String voornaam, String familienaam, Goedkeuring goedkeuring) {
    public PrestatieMetAssistentNaam (Prestatie prestatie){
        this(prestatie.getId(), prestatie.getNaam(),prestatie.getOmschrijving(), prestatie.getAssistent().getVoornaam(), prestatie.getAssistent().getFamilienaam(), prestatie.getGoedkeuring());
    }
}
