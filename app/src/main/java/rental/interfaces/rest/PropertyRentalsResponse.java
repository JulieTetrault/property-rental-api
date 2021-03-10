package rental.interfaces.rest;

import java.util.List;
import java.util.stream.Collectors;
import rental.domain.Rental;

public class PropertyRentalsResponse {

  public final List<PropertyRentalResponse> propertyRentalResponses;

  public PropertyRentalsResponse(List<Rental> rentals) {
    this.propertyRentalResponses = rentals.stream().map(rental -> new PropertyRentalResponse(rental)).collect(
        Collectors.toList());
  }
}
