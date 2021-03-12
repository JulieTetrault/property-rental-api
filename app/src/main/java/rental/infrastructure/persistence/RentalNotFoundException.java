package rental.infrastructure.persistence;

import rental.domain.rental.RentalIdentifier;
import rental.infrastructure.exception.ResourceNotFoundException;

public class RentalNotFoundException extends ResourceNotFoundException {

  private static final String CODE = "RENTAL_NOT_FOUND";
  private static final String MESSAGE = "Rental %s not found";

  public RentalNotFoundException(RentalIdentifier rentalId) {
    super(CODE, String.format(MESSAGE, rentalId.toString()));
  }
}
