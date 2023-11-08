package be.helpper.goedkeuringen;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/goedkeuringen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GoedkeuringResource {
    @Inject
    GoedkeuringService goedkeuringService;
    @POST
    @Path("/nieuw")
    public void createGoedkeuring(Goedkeuring goedkeuring){
        goedkeuringService.createGoedkeuring(goedkeuring);
    }
}
