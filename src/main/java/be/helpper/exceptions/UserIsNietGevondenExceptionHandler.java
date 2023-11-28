package be.helpper.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserIsNietGevondenExceptionHandler implements ExceptionMapper<UserIsNietGevondenException> {
    @Override
    public Response toResponse(UserIsNietGevondenException exception)
    {
        return Response.status(404, "Deze persoon is niet gevonden").build();
    }
}
