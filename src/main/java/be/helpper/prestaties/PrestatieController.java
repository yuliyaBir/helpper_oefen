package be.helpper.prestaties;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/prestaties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrestatieController {
    @Inject
    PrestatieService prestatieService;
    //private record nieuwTaak(@NotBlank String naam, @NotBlank String omschrijving){}
    @POST
    @Transactional
    public long create(Prestatie prestatie){
        return prestatieService.createPrestatie(prestatie);
    }

    @GET
    @Path("/{id}")
    public Prestatie findPrestatieById(@PathParam("id") long id){
        return prestatieService.findPrestatieById(id);
    }


}
