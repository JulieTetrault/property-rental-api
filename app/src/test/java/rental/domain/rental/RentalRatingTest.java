package rental.domain.rental;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RentalRatingTest {

  private static final String SOME_VALID_RENTAL_RATING_STRING = "3";
  private static final String SOME_INVALID_RENTAL_RATING_STRING = "7";

  @Test
  public void givenValidRentalRatingString_whenMappingToRentalRating_thenShouldNotThrowInvalidRentalRatingException() {

    assertDoesNotThrow(() -> {
      RentalRating.fromString(SOME_VALID_RENTAL_RATING_STRING);
    });
  }

  @Test
  public void givenInvalidRentalRatingString_whenMappingToRentalRating_thenShouldThrowInvalidRentalRatingException() {

    assertThrows(InvalidRentalRatingException.class, () -> {
      RentalRating.fromString(SOME_INVALID_RENTAL_RATING_STRING);
    });
  }
}
