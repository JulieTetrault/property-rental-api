package utility;

import com.github.javafaker.Faker;
import rental.application.RentalDTO;
import rental.domain.rental.RentalRating;

public class RentalDTOBuilder {

  private static final String SOME_ID = new Faker().internet().uuid();
  private static final String SOME_CITY = new Faker().address().city();
  private static final String SOME_POSTAL_CODE = new Faker().address().zipCode();
  private static final Integer SOME_PRICE = new Faker().random().nextInt(50, 300);
  private static final Integer SOME_NB_BEDS = new Faker().random().nextInt(1, 10);
  private static final Integer SOME_NB_BATHS = new Faker().random().nextInt(1, 10);
  private static final String SOME_OWNER = new Faker().name().fullName();
  private static final RentalRating SOME_RATING = RentalRating.FOUR;
  private static final String SOME_DESCRIPTION = new Faker().lorem().paragraph();

  private final String id;
  private final String city;
  private final String postalCode;
  private final Integer price;
  private final Integer nbBeds;
  private final Integer nbBaths;
  private final String owner;
  private final RentalRating rating;
  private final String description;

  public RentalDTOBuilder() {
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

  public RentalDTO build() {
    return new RentalDTO(this.id, this.city, this.postalCode, this.price, this.nbBeds, this.nbBaths, this.owner,
        this.rating, this.description);
  }
}
