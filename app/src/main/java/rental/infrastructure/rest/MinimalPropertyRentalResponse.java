package rental.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import rental.application.RentalDTO;

public class MinimalPropertyRentalResponse {

  public final String id;
  @JsonProperty("postalcode")
  public final String postalCode;
  public final Integer price;
  @JsonProperty("nb_beds")
  public final Integer nbBeds;
  public final Integer rating;

  @JsonCreator
  public MinimalPropertyRentalResponse(RentalDTO rentalDTO) {
    this.id = rentalDTO.id.toString();
    this.postalCode = rentalDTO.postalCode;
    this.price = rentalDTO.price;
    this.nbBeds = rentalDTO.nbBeds;
    this.rating = rentalDTO.rating.toInt();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MinimalPropertyRentalResponse)) {
      return false;
    }

    MinimalPropertyRentalResponse that = (MinimalPropertyRentalResponse) o;
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.postalCode, that.postalCode)
        && Objects.equals(this.price, that.price)
        && Objects.equals(this.nbBeds, that.nbBeds)
        && Objects.equals(this.rating, that.rating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, postalCode, price, nbBeds, rating);
  }
}
