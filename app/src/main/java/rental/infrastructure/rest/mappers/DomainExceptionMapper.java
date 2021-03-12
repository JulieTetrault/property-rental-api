package rental.infrastructure.rest.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import rental.domain.exception.DomainException;

@Provider
public class DomainExceptionMapper implements ExceptionMapper<DomainException> {

  @Override
  public Response toResponse(DomainException domainException) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(domainException.getCode(),
        domainException.getMessage());
    return Response.status(400).entity(exceptionResponse).build();
  }
}
