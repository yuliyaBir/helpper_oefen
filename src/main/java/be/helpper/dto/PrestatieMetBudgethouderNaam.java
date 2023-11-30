package be.helpper.dto;

import be.helpper.goedkeuringen.Goedkeuring;
import be.helpper.prestaties.Prestatie;

public record PrestatieMetBudgethouderNaam(long id, String naam, String omschrijving, String voornaam, String familienaam, Goedkeuring goedkeuringen){
    public PrestatieMetBudgethouderNaam (Prestatie prestatie){
        this(prestatie.getId(), prestatie.getNaam(),prestatie.getOmschrijving(), prestatie.getBudgethouder().getVoornaam(), prestatie.getBudgethouder().getFamilienaam(), prestatie.getGoedkeuring());
    }
}
