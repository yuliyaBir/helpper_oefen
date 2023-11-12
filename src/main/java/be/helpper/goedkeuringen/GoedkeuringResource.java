package be.helpper.goedkeuringen;

import be.helpper.prestaties.PrestatieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/goedkeuringen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GoedkeuringResource {
    @Inject
    GoedkeuringService goedkeuringService;
    @POST
    @Path("/nieuwVoorPrestatie/{id}")
    public void createGoedkeuring(@Valid Goedkeuring goedkeuring, @PathParam("id") long id){
        goedkeuringService.createGoedkeuringVoorPrestatie(id, goedkeuring);
    }
}
