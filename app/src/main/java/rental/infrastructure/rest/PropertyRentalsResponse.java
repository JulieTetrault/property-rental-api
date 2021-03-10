package rental.infrastructure.rest;

import java.util.List;
import java.util.stream.Collectors;
import rental.application.RentalDTO;

public class PropertyRentalsResponse {

  public final List<PropertyRentalResponse> propertyRentalResponses;

  public PropertyRentalsResponse(List<RentalDTO> rentalDTOs) {
    this.propertyRentalResponses = rentalDTOs.stream().map(rental -> new PropertyRentalResponse(rental)).collect(
        Collectors.toList());
  }
}
