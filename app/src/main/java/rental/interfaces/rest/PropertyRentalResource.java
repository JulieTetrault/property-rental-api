package rental.interfaces.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rental.domain.Rental;
import rental.domain.RentalRepository;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class PropertyRentalResource {

  private final RentalRepository rentalRepository;

  @Inject
  public PropertyRentalResource(RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRentals() {
    List<Rental> rentals = rentalRepository.getAll();

    List<PropertyRentalResponse> propertyRentalsResponse =
        rentals.stream().map(rental -> new PropertyRentalResponse(rental)).collect(
            Collectors.toList());

    return Response.status(Response.Status.OK)
        .type(MediaType.APPLICATION_JSON).entity(propertyRentalsResponse).build();
  }
}