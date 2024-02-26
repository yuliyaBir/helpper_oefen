package be.helpper.prestaties;

import be.helpper.dto.PrestatieWithAssistentName;
import be.helpper.dto.PrestatieWithBudgethouderName;
import be.helpper.exceptions.UserHasWrongRoleException;
import be.helpper.users.UserService;
import jakarta.annotation.security.RolesAllowed;
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

    public record NewPrestatie(@NotBlank String naam, @NotBlank String omschrijving, @NotNull @Positive long assistentId, @NotBlank String budgethouderVoornaam, @NotBlank String budgethouderFamilienaam){
    }
    private record PrestatieShort(long id, String naam, String omschrijving, String assistentVoornaam, String assistentFamilienaam, String budgethouderVoornaam, String budgethouderFamilienaam){
        private PrestatieShort(Prestatie prestatie) {
           this(prestatie.getId(), prestatie.getNaam(), prestatie.getOmschrijving(), prestatie.getAssistent().getVoornaam(), prestatie.getAssistent().getFamilienaam(),prestatie.getBudgethouder().getVoornaam(), prestatie.getBudgethouder().getFamilienaam());
        }
    }
    @POST
    @Path("/create")
    @RolesAllowed("assistent")
    @Transactional
    public Prestatie createPrestatie(@Valid PrestatieResource.NewPrestatie newPrestatie){
            var budgethouder = userService.findByFamilienaam(newPrestatie.budgethouderFamilienaam(), newPrestatie.budgethouderVoornaam());
            if (!budgethouder.getRol().equals("budgethouder")){
                throw new UserHasWrongRoleException("Deze persoon is geen budgethouder");            }
            var assistent = userService.findById(newPrestatie.assistentId()).orElseThrow(NotFoundException::new);
            var prestatie = new Prestatie(newPrestatie.naam, newPrestatie.omschrijving, assistent, budgethouder);
            var prestatieId = prestatieService.createPrestatie(prestatie);
            return   prestatieService.findPrestatieById(prestatieId).orElseThrow(NotFoundException::new);
    }
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/{id}")
    public Response findPrestatieById(@PathParam("id") long id){
        var prestatie = prestatieService.findPrestatieById(id).orElseThrow(NotFoundException::new);
        if (prestatie != null){
            return Response.ok(new PrestatieShort(prestatie)).build();
        } else{
            return Response.status(Response.Status.NOT_FOUND).entity("Prestatie is niet gevonden.").build();
        }
    }
    @GET
    @RolesAllowed("assistent")
    @Path("assistent/{assistentId}/withoutGoedkeuring")
    public List<PrestatieWithBudgethouderName> prestatiesWithoutGoedkeuringForASpecificAssistent(@PathParam("assistentId")long assistentId){
        return prestatieService.prestatiesWithoutGoedkeuringForASpecificAssistent(assistentId).stream().map(PrestatieWithBudgethouderName::new).toList();
    }
    @GET
    @RolesAllowed("budgethouder")
    @Path("budgethouder/{budgethouderId}/withoutGoedkeuring")
    public List<PrestatieWithAssistentName> prestatiesWithoutGoedkeuringForASpecificBudgethouder(@PathParam("budgethouderId") long budgethouderId){
        return prestatieService.prestatiesWithoutGoedkeuringForASpecificBudgethouder(budgethouderId).stream().map(PrestatieWithAssistentName::new).toList();
    }
    @GET
    @RolesAllowed("budgethouder")
    @Path("budgethouder/{budgethouderId}/withGoedkeuring")
    public List<PrestatieWithAssistentName> prestatiesWithGoedkeuringForASpecificBudgethouder(@PathParam("budgethouderId") long budgethouderId){
        return prestatieService.prestatiesWithGoedkeuringForASpecificBudgethouder(budgethouderId).stream().map(PrestatieWithAssistentName::new).toList();
    }
    @GET
    @RolesAllowed("assistent")
    @Path("assistent/{assistentId}/withGoedkeuring")
    public List<PrestatieWithBudgethouderName> prestatiesWithGoedkeuringForASpecificAssistent(@PathParam("assistentId")long assistentId){
        return prestatieService.prestatiesWithGoedkeuringForASpecificAssistent(assistentId).stream().map(PrestatieWithBudgethouderName::new).toList();
    }
    @DELETE
    @Transactional
    @RolesAllowed({"budgethouder", "assistent"})
    @Path("/delete/{id}")
    public void deletePrestatie(@PathParam("id") long id){
        prestatieService.deletePrestatie(id);
    }
}
