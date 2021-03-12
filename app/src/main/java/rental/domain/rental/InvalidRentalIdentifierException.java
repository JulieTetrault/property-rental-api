package rental.domain.rental;

import rental.domain.exception.DomainException;

public class InvalidRentalIdentifierException extends DomainException {

  private static final String CODE = "INVALID_RENTAL_ID";
  private static final String MESSAGE = "Rental ID format is invalid";

  public InvalidRentalIdentifierException() {
    super(CODE, MESSAGE);
  }
}
