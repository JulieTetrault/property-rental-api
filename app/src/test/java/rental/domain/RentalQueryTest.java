package rental.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.github.javafaker.Faker;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalQuery;
import rental.infrastructure.persistence.RentalBuilder;

public class RentalQueryTest {

  private static final Integer SOME_MINIMUM_NUMBER_OF_BEDS = 3;
  private static final Integer SOME_MINIMUM_PRICE = 50;
  private static final Integer SOME_MAXIMUM_PRICE = 100;
  private static final String SOME_POSTAL_CODE_PATTERN = "G3A__4";


  @Test
  public void givenRentalWithNumberOfBedsAboveMinimumNumberOfBeds_whenApplyingRentalQuery_thenShouldReturnTrue() {
    Rental rental = new RentalBuilder().withNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS + 1).build();
    RentalQuery rentalQuery = new RentalQuery.RentalQueryBuilder().witMinNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS).build();

    assertTrue(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithNumberOfBedsEqualToMinimumNumberOfBeds_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental = new RentalBuilder().withNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS).build();
    RentalQuery rentalQuery = new RentalQuery.RentalQueryBuilder().witMinNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS).build();

    assertFalse(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithNumberOfBedsUnderMinimumNumberOfBeds_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental = new RentalBuilder().withNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS - 1).build();
    RentalQuery rentalQuery = new RentalQuery.RentalQueryBuilder().witMinNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS).build();

    assertFalse(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPriceBetweenPriceRange_whenApplyingRentalQuery_thenShouldReturnTrue() {
    Rental rental = new RentalBuilder().withPrice(
        BigDecimal.valueOf(new Faker().number().numberBetween(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE))).build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPriceRange(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE).build();

    assertTrue(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPriceEqualToPriceRangeLowerLimit_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental = new RentalBuilder().withPrice(BigDecimal.valueOf(SOME_MINIMUM_PRICE)).build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPriceRange(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE).build();

    assertFalse(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPriceEqualToPriceRangeUpperLimit_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental = new RentalBuilder().withPrice(BigDecimal.valueOf(SOME_MAXIMUM_PRICE)).build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPriceRange(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE).build();

    assertFalse(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPriceUnderPriceRange_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental = new RentalBuilder().withPrice(BigDecimal.valueOf(SOME_MINIMUM_PRICE - 1)).build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPriceRange(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE).build();

    assertFalse(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPriceAbovePriceRange_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental = new RentalBuilder().withPrice(BigDecimal.valueOf(SOME_MAXIMUM_PRICE + 1)).build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPriceRange(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE).build();

    assertFalse(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPostalCodeMatchingPostalCodePattern_whenApplyingRentalQuery_thenShouldReturnTrue() {
    Rental rental =
        new RentalBuilder().withPostalCode(SOME_POSTAL_CODE_PATTERN.replaceAll("_", new Faker().lorem().characters(1)))
            .build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPostalCodePattern(SOME_POSTAL_CODE_PATTERN).build();

    assertTrue(rentalQuery.apply(rental));
  }

  @Test
  public void givenRentalWithPostalCodeNotMatchingPostalCodePattern_whenApplyingRentalQuery_thenShouldReturnFalse() {
    Rental rental =
        new RentalBuilder().withPostalCode(new Faker().lorem().characters(3))
            .build();
    RentalQuery rentalQuery =
        new RentalQuery.RentalQueryBuilder().withPostalCodePattern(SOME_POSTAL_CODE_PATTERN).build();

    assertFalse(rentalQuery.apply(rental));
  }
}
