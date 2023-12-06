package be.helpper.goedkeuringen;

import be.helpper.dto.NewGoedkeuring;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;

@Path("/goedkeuringen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GoedkeuringResource {
    @Inject
    GoedkeuringService goedkeuringService;
    @POST
    @RolesAllowed("budgethouder")
    @Path("/create/{prestatieId}")
    public long createGoedkeuring(@Valid NewGoedkeuring newGoedkeuring, @PathParam("prestatieId") long id){
        var goedkeuring = new Goedkeuring(LocalDate.now(), newGoedkeuring.commentaar(), newGoedkeuring.uren());
        return goedkeuringService.createGoedkeuringForPrestatie(id, goedkeuring);
    }
}
