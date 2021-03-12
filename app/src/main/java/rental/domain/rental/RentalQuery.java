package rental.domain.rental;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RentalQuery {

  private final Integer minNbBeds;
  private final Integer minPrice;
  private final Integer maxPrice;
  private final String postalCodePattern;

  private RentalQuery(RentalQueryBuilder builder) {
    this.minNbBeds = builder.minNbBeds;
    this.minPrice = builder.minPrice;
    this.maxPrice = builder.maxPrice;
    this.postalCodePattern = builder.postalCodePattern;
  }

  public boolean apply(Rental rental) {
    return isNbBedsValid(rental) && isPriceValid(rental) && isPostalCodeValid(rental);
  }

  private boolean isNbBedsValid(Rental rental) {
    if (minNbBeds != null) {
      return rental.getNbBeds() > minNbBeds;
    }
    return true;
  }

  private boolean isPriceValid(Rental rental) {
    boolean isMinPriceValid = true;
    boolean isMaxPriceValid = true;

    if (minPrice != null) {
      isMinPriceValid = rental.getPrice().intValue() > minPrice;
    }

    if (maxPrice != null) {
      isMaxPriceValid = rental.getPrice().intValue() < maxPrice;
    }

    return isMinPriceValid && isMaxPriceValid;
  }

  private boolean isPostalCodeValid(Rental rental) {
    if (postalCodePattern != null) {
      Pattern pattern = Pattern.compile(postalCodePattern.replaceAll("_", "."));
      Matcher matcher = pattern.matcher(rental.getPostalCode());

      return matcher.matches();
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
    private String postalCodePattern;

    public RentalQueryBuilder witMinNbBeds(Integer minNbBeds) {
      this.minNbBeds = minNbBeds;

      return this;
    }

    public RentalQueryBuilder withPriceRange(Integer minPrice, Integer maxPrice) {
      this.minPrice = minPrice;
      this.maxPrice = maxPrice;

      return this;
    }

    public RentalQueryBuilder withPostalCodePattern(String postalCodePattern) {
      this.postalCodePattern = postalCodePattern;

      return this;
    }

    public RentalQuery build() {
      return new RentalQuery(this);
    }
  }
}
