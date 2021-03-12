package rental.domain.rental;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class RentalIdentifierTest {

  private static final String SOME_VALID_RENTAL_ID_STRING = new Faker().internet().uuid();
  private static final String SOME_INVALID_RENTAL_ID_STRING = new Faker().lorem().characters(3);

  @Test
  public void givenValidRentalIDString_whenMappingToRentalIdentifier_thenShouldNotThrowInvalidRentalIdentifierException() {

    assertDoesNotThrow(() -> {
      RentalIdentifier.fromString(SOME_VALID_RENTAL_ID_STRING);
    });
  }

  @Test
  public void givenInvalidRentalIDString_whenMappingToRentalIdentifier_thenShouldThrowInvalidRentalIdentifierException() {

    assertThrows(InvalidRentalIdentifierException.class, () -> {
      RentalIdentifier.fromString(SOME_INVALID_RENTAL_ID_STRING);
    });
  }
}
