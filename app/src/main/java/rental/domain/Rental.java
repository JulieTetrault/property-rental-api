package rental.domain;

import java.math.BigDecimal;

public class Rental {

  private String id;
  private String city;
  private String postalCode;
  private BigDecimal price;
  private Integer nbBeds;
  private Integer nbBaths;
  private String owner;
  private Integer rating;
  private String description;

  public Rental(String id, String city, String postalCode, BigDecimal price, Integer nbBeds, Integer nbBaths,
                String owner, Integer rating, String description) {
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

  public String getId() {
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

  public Integer getRating() {
    return this.rating;
  }

  public String getDescription() {
    return this.description;
  }
}
