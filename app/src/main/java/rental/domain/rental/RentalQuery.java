package rental.domain.rental;

import java.util.Objects;

public class RentalQuery {

  private final Integer minNbBeds;
  private final Integer minPrice;
  private final Integer maxPrice;

  private RentalQuery(RentalQueryBuilder builder) {
    this.minNbBeds = builder.minNbBeds;
    this.minPrice = builder.minPrice;
    this.maxPrice = builder.maxPrice;
  }

  public boolean apply(Rental rental) {
    return isNbBedsValid(rental) && isPriceValid(rental);
  }

  private boolean isNbBedsValid(Rental rental) {
    if (minNbBeds != null) {
      return rental.getNbBeds() > minNbBeds;
    }
    return true;
  }

  private boolean isPriceValid(Rental rental) {
    if (minPrice != null) {
      return rental.getPrice().intValue() > minPrice;
    }

    if (maxPrice != null) {
      return rental.getPrice().intValue() < maxPrice;
    }

    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RentalQuery that = (RentalQuery) o;
    return Objects.equals(this.minNbBeds, that.minNbBeds)
        && Objects.equals(this.minPrice, that.minPrice)
        && Objects.equals(this.maxPrice, that.maxPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minNbBeds, minPrice, maxPrice);
  }

  public static class RentalQueryBuilder {

    private Integer minNbBeds;
    private Integer minPrice;
    private Integer maxPrice;

    public RentalQueryBuilder witMinNbBeds(Integer minNbBeds) {
      this.minNbBeds = minNbBeds;

      return this;
    }

    public RentalQueryBuilder withPriceRange(Integer minPrice, Integer maxPrice) {
      this.minPrice = minPrice;
      this.maxPrice = maxPrice;

      return this;
    }

    public RentalQuery build() {
      return new RentalQuery(this);
    }
  }
}
