package be.helpper.prestaties;

import be.helpper.goedkeuringen.Goedkeuring;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Set;

@Path("/prestaties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrestatieController {
    @Inject
    PrestatieService prestatieService;
    private record prestatieMetBudgethouderNaam(long id, String naam, String omschrijving, String voornaam, String familienaam, Set<Goedkeuring> goedkeuringen){}

    @POST
    @Path("/nieuw")
    @Transactional
    public long create(Prestatie prestatie){
//        var prestatie = new Prestatie("test", "test", 1,2);
        return prestatieService.createPrestatie(prestatie);
    }

    @GET
    @Path("/{id}")
    public Prestatie findPrestatieById(@PathParam("id") long id){
        return prestatieService.findPrestatieById(id);
    }

//    @GET
//    @Path("/zonderGoedkeuring")
//    public List<Prestatie> findPrestatiesZonderGoedkeuring(){
//        return prestatieService.findPrestatiesZonderGoedkeuring().stream().map(prestatie ->
//                new PrestatieController.prestatieMetBudgethouderNaam());
//    }
    @GET
    @Path("/metGoedkeuring")
    public List<Prestatie> lijstVanPrestatiesMetGoedkeuring(){
        return prestatieService.lijstVanPrestatiesMetGoedkeuring();
    }

}
