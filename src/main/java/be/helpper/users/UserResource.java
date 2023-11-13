package be.helpper.users;


import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


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
    public User findByEmail(String email) {
        return service.findByEmail(email);
    }
    @GET
    @RolesAllowed({"assistent", "budgethouder"})
    @Path("/familienaamEnVoornaam")
    public Response.ResponseBuilder findByFamilienaam(@QueryParam("familienaam") String familienaam, @QueryParam("voornaam") String voornaam){
        var user =  service.findByFamilienaam(familienaam, voornaam);
        if (user != null){
            return Response.ok(user,MediaType.APPLICATION_JSON_TYPE);
        } else {
            return Response.status(404, "User is niet gevonden.");
        }
    }
//    @GET
//    @Path("/{id}")
//    public Response.ResponseBuilder findById(@PathParam("id") long id) {
//        var user =  service.findById(id);
//        if (user != null){
//        return Response.ok(user,MediaType.APPLICATION_JSON_TYPE);
//        } else {
//            return Response.status(404, "User is niet gevonden.");
//        }
//    }
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
