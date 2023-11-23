package be.helpper.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserIsAlBestaanExceptionHandler implements ExceptionMapper<UserIsAlBestaanException> {
    @Override
    public Response toResponse(UserIsAlBestaanException exception)
    {
        return Response.status(409, "Persoon met deze email is al bestaan").build();
    }
}
