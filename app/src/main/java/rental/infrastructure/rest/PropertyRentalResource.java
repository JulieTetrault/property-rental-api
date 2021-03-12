package rental.infrastructure.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rental.application.RentalDTO;
import rental.application.RentalService;
import rental.domain.rental.RentalQuery;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class PropertyRentalResource {

  private final RentalService rentalService;

  @Inject
  public PropertyRentalResource(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GET
  public Response getRentals(@QueryParam("min_nb_beds") Integer minNbBeds,
                             @QueryParam("postalcode") String postalCode,
                             @QueryParam("min_price") Integer minPrice,
                             @QueryParam("max_price") Integer maxPrice) {
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().witMinNbBeds(minNbBeds).withPriceRange(minPrice, maxPrice)
            .withPostalCodePattern(postalCode)
            .build();

    List<RentalDTO> rentalDTOs = rentalService.getAllRentals(rentalQuery);

    List<MinimalPropertyRentalResponse> propertyRentalsResponse =
        rentalDTOs.stream().map(rental -> new MinimalPropertyRentalResponse(rental)).collect(
            Collectors.toList());

    return Response.status(Response.Status.OK)
        .type(MediaType.APPLICATION_JSON).entity(propertyRentalsResponse).build();
  }

  @GET
  @Path("{id}")
  public Response getRental(@PathParam("id") String rentalId) {
    RentalDTO rentalDTO = rentalService.getRental(rentalId);

    PropertyRentalResponse propertyRentalResponse = new PropertyRentalResponse(rentalDTO);

    return Response.status(Response.Status.OK)
        .type(MediaType.APPLICATION_JSON).entity(propertyRentalResponse).build();
  }
}