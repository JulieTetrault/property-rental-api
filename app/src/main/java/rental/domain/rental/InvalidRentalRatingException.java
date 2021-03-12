package rental.domain.rental;

import rental.domain.exception.DomainException;

public class InvalidRentalRatingException extends DomainException {

  private static final String CODE = "INVALID_RENTAL_RATING";
  private static final String MESSAGE = "Rental rating %s is not a valid value";

  public InvalidRentalRatingException(String rentalRating) {
    super(CODE, String.format(MESSAGE, rentalRating));
  }
}
