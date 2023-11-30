package be.helpper.goedkeuringen;

import be.helpper.dto.NieuweGoedkeuring;
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
    @Path("/nieuwVoorPrestatie/{id}")
    public long createGoedkeuring(@Valid NieuweGoedkeuring nieuweGoedkeuring, @PathParam("id") long id){
        var goedkeuring = new Goedkeuring(LocalDate.now(), nieuweGoedkeuring.commentaar(), nieuweGoedkeuring.uren());
        return goedkeuringService.createGoedkeuringVoorPrestatie(id, goedkeuring);
    }
}
