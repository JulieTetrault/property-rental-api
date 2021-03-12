package rental.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import rental.application.RentalDTO;

public class PropertyRentalResponse {

  @JsonProperty("postalcode")
  public final String postalCode;
  public final String city;
  public final String description;
  public final Integer price;
  public final String owner;
  @JsonProperty("nb_beds")
  public final Integer nbBeds;
  @JsonProperty("nb_baths")
  public final Integer nbBaths;
  public final Integer rating;

  @JsonCreator
  public PropertyRentalResponse(RentalDTO rentalDTO) {
    this.postalCode = rentalDTO.postalCode;
    this.city = rentalDTO.city;
    this.description = rentalDTO.description;
    this.price = rentalDTO.price;
    this.owner = rentalDTO.owner;
    this.nbBeds = rentalDTO.nbBeds;
    this.nbBaths = rentalDTO.nbBaths;
    this.rating = rentalDTO.rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PropertyRentalResponse that = (PropertyRentalResponse) o;
    return Objects.equals(this.postalCode, that.postalCode)
        && Objects.equals(this.city, that.city)
        && Objects.equals(this.description, that.description)
        && Objects.equals(this.price, that.price)
        && Objects.equals(this.owner, that.owner)
        && Objects.equals(this.nbBeds, that.nbBeds)
        && Objects.equals(this.nbBaths, that.nbBaths)
        && Objects.equals(this.rating, that.rating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postalCode, city, description, price, owner, nbBeds, nbBaths, rating);
  }
}
