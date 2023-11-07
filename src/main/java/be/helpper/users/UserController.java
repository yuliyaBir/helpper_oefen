package be.helpper.users;

import be.helpper.exceptions.ReaderExceptionMapper;
import be.helpper.prestaties.Prestatie;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestMulti;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserService service;
    Multi<String> logs;
    @GET
    @Path("/{id}")
    public User findById(Long id){
        var user = service.findById(id);
        if (user == null){
            throw new UserIsNietGevondenException();
        }
        return user;
    }

}
