package rental.infrastructure.persistence;

import java.math.BigDecimal;
import rental.domain.rental.Rental;

public class RentalBuilder {

  private static final String SOME_ID = "id";
  private static final String SOME_CITY = "city";
  private static final String SOME_POSTAL_CODE = "postalCode";
  private static final BigDecimal SOME_PRICE = new BigDecimal(30);
  private static final Integer SOME_NB_BEDS = 4;
  private static final Integer SOME_NB_BATHS = 3;
  private static final String SOME_OWNER = "owner";
  private static final Integer SOME_RATING = 2;
  private static final String SOME_DESCRIPTION = "description";

  private final String id;
  private final String city;
  private final String postalCode;
  private final BigDecimal price;
  private final Integer nbBeds;
  private final Integer nbBaths;
  private final String owner;
  private final Integer rating;
  private final String description;

  public RentalBuilder() {
    this.id = SOME_ID;
    this.city = SOME_CITY;
    this.postalCode = SOME_POSTAL_CODE;
    this.price = SOME_PRICE;
    this.nbBeds = SOME_NB_BEDS;
    this.nbBaths = SOME_NB_BATHS;
    this.owner = SOME_OWNER;
    this.rating = SOME_RATING;
    this.description = SOME_DESCRIPTION;
  }

  public Rental build() {
    return new Rental(this.id, this.city, this.postalCode, this.price, this.nbBeds, this.nbBaths, this.owner,
        this.rating, this.description);
  }
}