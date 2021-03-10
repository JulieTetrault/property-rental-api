package rental.interfaces.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import rental.domain.Rental;

public class PropertyRentalResponse {

  public final String id;
  @JsonProperty("postalcode")
  public final String postalCode;
  public final Integer price;
  @JsonProperty("nb_beds")
  public final Integer nbBeds;
  public final Integer rating;

  @JsonCreator
  public PropertyRentalResponse(Rental rental) {
    this.id = rental.getId();
    this.postalCode = rental.getPostalCode();
    this.price = rental.getPrice().intValue();
    this.nbBeds = rental.getNbBeds();
    this.rating = rental.getRating();
  }
}
