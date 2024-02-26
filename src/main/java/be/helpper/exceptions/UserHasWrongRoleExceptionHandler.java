package be.helpper.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserHasWrongRoleExceptionHandler implements ExceptionMapper<UserHasWrongRoleException> {
    @Override
    public Response toResponse(UserHasWrongRoleException exception)
    {
        return Response.status(400, "Deze persoon is geen budgethouder").build();
    }
}
