package be.helpper.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserHeeftVerkeerdeRolExceptionHandler implements ExceptionMapper<UserHeeftVerkeerdeRolException> {
    @Override
    public Response toResponse(UserHeeftVerkeerdeRolException exception)
    {
        return Response.status(400, "Deze persoon is geen budgethouder").build();
    }

}
