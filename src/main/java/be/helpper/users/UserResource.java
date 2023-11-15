package be.helpper.users;


import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jboss.logging.annotations.Param;


@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class UserResource {
    @Inject
    UserService service;
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/{id}")
    public User findById(@PathParam("id") long id) {
        return service.findById(id).orElseThrow(NotFoundException::new);
    }
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/email")
    public Response findByEmail(@QueryParam("email") String email) {
        User user = service.findByEmail(email).orElseThrow(NotFoundException::new);

        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found for email: " + email).build();
        }
    }
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/familienaamEnVoornaam")
    public Response findByFamilienaam(@QueryParam("familienaam") String familienaam, @QueryParam("voornaam") String voornaam){
        var user =  service.findByFamilienaam(familienaam, voornaam);
        if (user != null){
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("User is niet gevonden.").build();
        }
    }
    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    @PermitAll
    @Path("/nieuweUser")
    public Response createUser(@Valid User user, @Context UriInfo uriInfo){
        var user1 = service.createUser(user);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(user1.getId()));
        return Response.created(builder.build()).build();
    }
    @Transactional(Transactional.TxType.REQUIRED)
    @DELETE
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/delete/{id}")
    public void deleteUser(@PathParam("id") long id){
        service.deleteUser(id);
    }
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/me")
    public String me(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }
}
