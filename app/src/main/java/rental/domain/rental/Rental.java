package rental.domain.rental;

import java.math.BigDecimal;

public class Rental {

  private RentalIdentifier id;
  private String city;
  private String postalCode;
  private BigDecimal price;
  private Integer nbBeds;
  private Integer nbBaths;
  private String owner;
  private RentalRating rating;
  private String description;

  public Rental(RentalIdentifier id, String city, String postalCode, BigDecimal price, Integer nbBeds, Integer nbBaths,
                String owner, RentalRating rating, String description) {
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

  public RentalIdentifier getId() {
    return this.id;
  }

  public String getCity() {
    return this.city;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public Integer getNbBeds() {
    return this.nbBeds;
  }

  public Integer getNbBaths() {
    return this.nbBaths;
  }

  public String getOwner() {
    return this.owner;
  }

  public RentalRating getRating() {
    return this.rating;
  }

  public String getDescription() {
    return this.description;
  }
}
