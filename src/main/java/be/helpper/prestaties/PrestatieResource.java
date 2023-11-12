package be.helpper.prestaties;

import be.helpper.dto.PrestatieMetAssistentNaam;
import be.helpper.dto.PrestatieMetBudgethouderNaam;
import be.helpper.users.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/prestaties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrestatieResource {
    @Inject
    PrestatieService prestatieService;
    @Inject
    UserService userService;

    // maak nieuwe prestatie
    public record NieuwePrestatie(@NotBlank String naam, @NotBlank String omschrijving, @NotNull @Positive long assistentId, @NotBlank String budgethouderVoornaam, @NotBlank String budgethouderFamilienaam){
    }
    @POST
    @Path("/nieuw")
    @Transactional
    public void createPrestatie(@Valid NieuwePrestatie nieuwePrestatie){
        var budgethouder = userService.findByFamilienaam(nieuwePrestatie.budgethouderFamilienaam(), nieuwePrestatie.budgethouderVoornaam());
        var assistent = userService.findById(nieuwePrestatie.assistentId()).orElseThrow(NotFoundException::new);
        var prestatie = new Prestatie(nieuwePrestatie.naam, nieuwePrestatie.omschrijving, assistent, budgethouder);
        prestatieService.createPrestatie(prestatie);
    }
    // get prestatieById
    @GET
    @Path("/{id}")
    public Response.ResponseBuilder findPrestatieById(@PathParam("id") long id){
        var prestatie = prestatieService.findPrestatieById(id).orElseThrow(NotFoundException::new);
        if (prestatie != null){
            return Response.ok(prestatie,MediaType.APPLICATION_JSON_TYPE);
        } else{
            return Response.status(404, "Prestatie is nit gevonden.");
        }
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
