package rental.infrastructure.rest.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import rental.infrastructure.exception.ResourceNotFoundException;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

  @Override
  public Response toResponse(ResourceNotFoundException resourceNotFoundException) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(resourceNotFoundException.getCode(),
        resourceNotFoundException.getMessage());
    return Response.status(404).entity(exceptionResponse).build();
  }
}
