package be.helpper.prestaties;

import be.helpper.dto.PrestatieMetAssistentNaam;
import be.helpper.dto.PrestatieMetBudgethouderNaam;
import be.helpper.users.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/prestaties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrestatieController {
    @Inject
    PrestatieService prestatieService;
    @Inject
    UserService userService;

    // maak nieuwe prestatie
    private record NieuwePrestatie(String naam, String omschrijving, long assistentId, String budgethouderVoornaam, String budgethouderFamilienaam){
    }
    @POST
    @Path("/nieuw")
    @Transactional
    public long create(NieuwePrestatie nieuwePrestatie){
        var budgethouder = userService.findByFamilienaam(nieuwePrestatie.budgethouderFamilienaam(), nieuwePrestatie.budgethouderVoornaam());
        var assistent = userService.findById(nieuwePrestatie.assistentId());
        var prestatie = new Prestatie(nieuwePrestatie.naam, nieuwePrestatie.omschrijving, assistent, budgethouder);
        return prestatieService.createPrestatie(prestatie);
    }
    // get prestatieById
    @GET
    @Path("/{id}")
    public Prestatie findPrestatieById(@PathParam("id") long id){
        return prestatieService.findPrestatieById(id);
    }

    // get lijst met prestaties zonder goedkeuring voor assistent
    @GET
    @Path("assistent/zonderGoedkeuring")
    public List<PrestatieMetBudgethouderNaam> findPrestatiesZonderGoedkeuringVoorAssistent(){
        return prestatieService.findPrestatiesZonderGoedkeuring().stream().map(PrestatieMetBudgethouderNaam::new).toList();
    }
    // get lijst met prestaties zonder goedkeuring voor budgethouder
    @GET
    @Path("budgethouder/zonderGoedkeuring")
    public List<PrestatieMetAssistentNaam> findPrestatiesZonderGoedkeuringVoorBudgethouder(){
        return prestatieService.findPrestatiesZonderGoedkeuring().stream().map(PrestatieMetAssistentNaam::new).toList();
    }
    // get lijst met prestaties met goedkeuring voor assistent
    @GET
    @Path("assistent/metGoedkeuring")
    public List<PrestatieMetBudgethouderNaam> lijstVanPrestatiesMetGoedkeuringVoorAssistent(){
        return prestatieService.lijstVanPrestatiesMetGoedkeuring().stream().map(PrestatieMetBudgethouderNaam::new).toList();
    }
    // get lijst met prestaties met goedkeuring voor budgethouder
    @GET
    @Path("budgethouder/metGoedkeuring")
    public List<PrestatieMetAssistentNaam> lijstVanPrestatiesMetGoedkeuringVoorBudgethouder(){
        return prestatieService.lijstVanPrestatiesMetGoedkeuring().stream().map(PrestatieMetAssistentNaam::new).toList();
    }
}
