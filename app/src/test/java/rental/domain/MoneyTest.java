package rental.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

public class MoneyTest {

  private static final Money SMALLEST_MONEY_AMOUNT = new Money(3);
  private static final Money GREATEST_MONEY_AMOUNT = new Money(10);

  @Test
  public void givenMoneyAmountGreaterThanOtherMoneyAmount_whenCheckingIfGreaterThan_thenShouldReturnTrue() {
    assertTrue(GREATEST_MONEY_AMOUNT.isGreaterThan(SMALLEST_MONEY_AMOUNT));
  }

  @Test
  public void givenMoneyAmountGreaterThanOtherMoneyAmount_whenCheckingIfLessThan_thenShouldReturnFalse() {
    assertFalse(GREATEST_MONEY_AMOUNT.isLessThan(SMALLEST_MONEY_AMOUNT));
  }

  @Test
  public void givenMoneyAmountLessThanOtherMoneyAmount_whenCheckingIfLessThan_thenShouldReturnTrue() {
    assertTrue(SMALLEST_MONEY_AMOUNT.isLessThan(GREATEST_MONEY_AMOUNT));
  }

  @Test
  public void givenMoneyAmountLessThanOtherMoneyAmount_whenCheckingIfGreaterThan_thenShouldReturnFalse() {
    assertFalse(SMALLEST_MONEY_AMOUNT.isGreaterThan(GREATEST_MONEY_AMOUNT));
  }
}
