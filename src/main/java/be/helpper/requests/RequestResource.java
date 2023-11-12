package be.helpper.requests;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/requests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequestResource {
    @Inject
    RequestService requestService;
    @POST
    @Path("/new")
    public long create(@RequestBody Request request) {
        return requestService.createRequest(request);
    }
}
