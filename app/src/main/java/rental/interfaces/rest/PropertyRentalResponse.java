package rental.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import rental.application.RentalDTO;

public class PropertyRentalResponse {

  public final String id;
  @JsonProperty("postalcode")
  public final String postalCode;
  public final Integer price;
  @JsonProperty("nb_beds")
  public final Integer nbBeds;
  public final Integer rating;

  @JsonCreator
  public PropertyRentalResponse(RentalDTO rentalDTO) {
    this.id = rentalDTO.id;
    this.postalCode = rentalDTO.postalCode;
    this.price = rentalDTO.price;
    this.nbBeds = rentalDTO.nbBeds;
    this.rating = rentalDTO.rating;
  }
}
