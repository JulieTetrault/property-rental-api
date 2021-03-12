package rental.application;

import java.util.List;
import java.util.stream.Collectors;
import rental.domain.rental.Rental;

public class RentalAssembler {

  public RentalDTO toDto(Rental rental) {
    return new RentalDTO(rental.getId(), rental.getCity(), rental.getPostalCode(), rental.getPrice().toInt(),
        rental.getNbBeds(), rental.getNbBaths(), rental.getOwner(), rental.getRating(), rental.getDescription());
  }

  public List<RentalDTO> toDto(List<Rental> rentals) {
    return rentals.stream().map(this::toDto).collect(Collectors.toList());
  }
}
