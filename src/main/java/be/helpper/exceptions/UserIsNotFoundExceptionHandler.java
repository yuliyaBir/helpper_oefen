package be.helpper.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserIsNotFoundExceptionHandler implements ExceptionMapper<UserIsNotFoundException> {
    @Override
    public Response toResponse(UserIsNotFoundException exception)
    {
        return Response.status(404, "Deze persoon is niet gevonden").build();
    }
}
