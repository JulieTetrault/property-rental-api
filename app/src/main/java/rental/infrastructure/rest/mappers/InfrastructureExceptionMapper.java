package rental.infrastructure.rest.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import rental.infrastructure.exception.InfrastructureException;

@Provider
public class InfrastructureExceptionMapper implements ExceptionMapper<InfrastructureException> {

  @Override
  public Response toResponse(InfrastructureException infrastructureException) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(infrastructureException.getCode(),
        infrastructureException.getMessage());
    return Response.status(400).entity(exceptionResponse).build();
  }
}