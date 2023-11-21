package be.helpper.prestaties;

import be.helpper.dto.PrestatieMetAssistentNaam;
import be.helpper.dto.PrestatieMetBudgethouderNaam;
import be.helpper.users.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.DuplicateKeyException;
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


    public record NieuwePrestatie(@NotBlank String naam, @NotBlank String omschrijving, @NotNull @Positive long assistentId, @NotBlank String budgethouderVoornaam, @NotBlank String budgethouderFamilienaam){
    }
    private record PrestatieBeknopt(long id, String naam, String omschrijving, String assistentVoornaam, String assistentFamilienaam, String budgethouderVoornaam, String budgethouderFamilienaam){
        private PrestatieBeknopt(Prestatie prestatie) {
           this(prestatie.getId(), prestatie.getNaam(), prestatie.getOmschrijving(), prestatie.getAssistent().getVoornaam(), prestatie.getAssistent().getFamilienaam(),prestatie.getBudgethouder().getVoornaam(), prestatie.getBudgethouder().getFamilienaam());
        }
    }
    // maak nieuwe prestatie
    @POST
    @Path("/nieuw")
    @RolesAllowed("assistent")
    @Transactional
    public Prestatie createPrestatie(@Valid NieuwePrestatie nieuwePrestatie) throws DuplicateKeyException{
            var budgethouder = userService.findByFamilienaam(nieuwePrestatie.budgethouderFamilienaam(), nieuwePrestatie.budgethouderVoornaam());
            var assistent = userService.findById(nieuwePrestatie.assistentId()).orElseThrow(NotFoundException::new);
            var prestatie = new Prestatie(nieuwePrestatie.naam, nieuwePrestatie.omschrijving, assistent, budgethouder);
            var prestatieId = prestatieService.createPrestatie(prestatie);
            return   prestatieService.findPrestatieById(prestatieId).orElseThrow(NotFoundException::new);
    }
    // get prestatieById
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/{id}")
    public Response findPrestatieById(@PathParam("id") long id){
        var prestatie = prestatieService.findPrestatieById(id).orElseThrow(NotFoundException::new);
        if (prestatie != null){
            return Response.ok(new PrestatieBeknopt(prestatie)).build();
        } else{
            return Response.status(Response.Status.NOT_FOUND).entity("Prestatie is niet gevonden.").build();
        }
    }

    // get lijst met prestaties zonder goedkeuring voor assistent
    @GET
    @RolesAllowed("assistent")
    @Path("assistent/{assistentId}/zonderGoedkeuring")
    public List<PrestatieMetBudgethouderNaam> lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeAssistent(@PathParam("assistentId")long assistentId){
        return prestatieService.lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeAssistent(assistentId).stream().map(PrestatieMetBudgethouderNaam::new).toList();
    }
    // get lijst met prestaties zonder goedkeuring voor budgethouder
    @GET
    @RolesAllowed("budgethouder")
    @Path("budgethouder/{budgethouderId}/zonderGoedkeuring")
    public List<PrestatieMetAssistentNaam> lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeBudgethouder(@PathParam("budgethouderId") long budgethouderId){
        return prestatieService.lijstVanPrestatiesZonderGoedkeuringVoorBepaaldeBudgethouder(budgethouderId).stream().map(PrestatieMetAssistentNaam::new).toList();
    }
    // get lijst met prestaties met goedkeuring voor budgethouder
    @GET
    @RolesAllowed("budgethouder")
    @Path("budgethouder/{budgethouderId}/metGoedkeuring")
    public List<PrestatieMetAssistentNaam> lijstVanPrestatiesMetGoedkeuringVoorBepaaldeBudgethouder(@PathParam("budgethouderId") long budgethouderId){
        return prestatieService.lijstVanPrestatiesMetGoedkeuringVoorBepaaldeBudgethouder(budgethouderId).stream().map(PrestatieMetAssistentNaam::new).toList();
    }
    // get lijst met prestaties met goedkeuring voor assistent
    @GET
    @RolesAllowed("assistent")
    @Path("assistent/{assistentId}/metGoedkeuring")
    public List<PrestatieMetBudgethouderNaam> lijstVanPrestatiesMetGoedkeuringVoorBepaaldeAssistent(@PathParam("assistentId")long assistentId){
        return prestatieService.lijstVanPrestatiesMetGoedkeuringVoorBepaaldeAssistent(assistentId).stream().map(PrestatieMetBudgethouderNaam::new).toList();
    }

    @DELETE
    @Transactional
    @RolesAllowed({"budgethouder", "assistent"})
    @Path("/delete/{id}")
    public void deletePrestatie(@PathParam("id") long id){
        prestatieService.deletePrestatie(id);
    }
}
