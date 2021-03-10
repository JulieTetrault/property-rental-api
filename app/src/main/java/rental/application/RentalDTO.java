package rental.application;

public class RentalDTO {

  public final String id;
  public final String city;
  public final String postalCode;
  public final Integer price;
  public final Integer nbBeds;
  public final Integer nbBaths;
  public final String owner;
  public final Integer rating;
  public final String description;

  public RentalDTO(String id, String city, String postalCode, Integer price, Integer nbBeds, Integer nbBaths,
                   String owner, Integer rating,
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
