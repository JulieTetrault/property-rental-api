package rental.application;

import rental.domain.rental.RentalIdentifier;
import rental.domain.rental.RentalRating;

public class RentalDTO {

  public final RentalIdentifier id;
  public final String city;
  public final String postalCode;
  public final Integer price;
  public final Integer nbBeds;
  public final Integer nbBaths;
  public final String owner;
  public final RentalRating rating;
  public final String description;

  public RentalDTO(RentalIdentifier id, String city, String postalCode, Integer price, Integer nbBeds, Integer nbBaths,
                   String owner, RentalRating rating,
                   String description) {
    this.id = id;
    this.city = city;
    this.postalCode = postalCode;
    this.price = price;
    this.nbBeds = nbBeds;
    this.nbBaths = nbBaths;
    this.owner = owner;
    this.rating = rating;
    this.description = description;
  }
}
