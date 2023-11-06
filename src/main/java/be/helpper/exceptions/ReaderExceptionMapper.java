package be.helpper.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ReaderExceptionMapper extends Throwable implements ExceptionMapper<ReaderExceptionMapper> {
    @Override
    public Response toResponse(ReaderExceptionMapper exception) {
     if( exception.getCause() instanceof JsonParseException ||
            exception.getCause() instanceof JsonMappingException ) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("error parsing json body - " + exception.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(exception.getMessage())
            .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
}
}
