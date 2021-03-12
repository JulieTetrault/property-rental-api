package rental.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

  private final BigDecimal amount;

  public Money(Integer amount) {
    this.amount = new BigDecimal(amount);
  }

  public Integer toInt() {
    return amount.intValue();
  }

  public boolean isGreaterThan(Money other) {
    return amount.compareTo(other.amount) > 0;
  }

  public boolean isLessThan(Money other) {
    return amount.compareTo(other.amount) < 0;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Money)) {
      return false;
    }

    Money that = (Money) o;
    return Objects.equals(this.amount, that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }
}
