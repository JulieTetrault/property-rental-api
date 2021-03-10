package rental.infrastructure.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rental.application.RentalDTO;
import rental.application.RentalService;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class PropertyRentalResource {

  private final RentalService rentalService;

  @Inject
  public PropertyRentalResource(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRentals() {
    List<RentalDTO> rentalDTOs = rentalService.getAllRentals();

    List<PropertyRentalResponse> propertyRentalsResponse =
        rentalDTOs.stream().map(rental -> new PropertyRentalResponse(rental)).collect(
            Collectors.toList());

    return Response.status(Response.Status.OK)
        .type(MediaType.APPLICATION_JSON).entity(propertyRentalsResponse).build();
  }
}