package be.helpper.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GoedgekeuringIsAlToegevoegdExceptionHandler implements ExceptionMapper<GoedkeuringIsAlToegevoegdException> {
    @Override
    public Response toResponse(GoedkeuringIsAlToegevoegdException exception)
    {
        return Response.status(409, "Goedkeuring voor deze prestatie is al toegevoegd.").build();
    }
}
