package utility;

import com.github.javafaker.Faker;
import rental.domain.Money;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalIdentifier;
import rental.domain.rental.RentalRating;

public class RentalBuilder {

  private static final RentalIdentifier SOME_ID = RentalIdentifier.fromString(new Faker().internet().uuid());
  private static final String SOME_CITY = new Faker().address().city();
  private static final String SOME_POSTAL_CODE = new Faker().address().zipCode();
  private static final Money SOME_PRICE = new Money(30);
  private static final Integer SOME_NB_BEDS = new Faker().random().nextInt(1, 10);
  private static final Integer SOME_NB_BATHS = new Faker().random().nextInt(1, 10);
  private static final String SOME_OWNER = new Faker().name().fullName();
  private static final RentalRating SOME_RATING = RentalRating.FOUR;
  private static final String SOME_DESCRIPTION = new Faker().lorem().paragraph();

  private RentalIdentifier id;
  private String city;
  private String postalCode;
  private Money price;
  private Integer nbBeds;
  private Integer nbBaths;
  private String owner;
  private RentalRating rating;
  private String description;

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

  public RentalBuilder withPostalCode(String postalCode) {
    this.postalCode = postalCode;

    return this;
  }

  public RentalBuilder withPrice(Money price) {
    this.price = price;

    return this;
  }

  public RentalBuilder withNbBeds(Integer nbBeds) {
    this.nbBeds = nbBeds;

    return this;
  }

  public Rental build() {
    return new Rental(this.id, this.city, this.postalCode, this.price, this.nbBeds, this.nbBaths, this.owner,
        this.rating, this.description);
  }
}
