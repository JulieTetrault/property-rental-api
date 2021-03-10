package rental.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
}
