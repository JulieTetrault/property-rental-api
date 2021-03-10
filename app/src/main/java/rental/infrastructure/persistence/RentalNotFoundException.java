package rental.infrastructure.persistence;

import rental.infrastructure.exception.InfrastructureException;

public class RentalNotFoundException extends InfrastructureException {

  private static final String CODE = "RENTAL_NOT_FOUND";
  private static final String MESSAGE = "Rental %s not found";

  public RentalNotFoundException(String rentalId) {
    super(CODE,  String.format(MESSAGE, rentalId));
  }
}
