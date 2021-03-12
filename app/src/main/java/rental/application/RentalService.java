package rental.application;

import java.util.List;
import javax.inject.Inject;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalQuery;
import rental.domain.rental.RentalRepository;

public class RentalService {

  private final RentalRepository rentalRepository;
  private final RentalAssembler rentalAssembler;

  @Inject
  public RentalService(RentalRepository rentalRepository, RentalAssembler rentalAssembler) {
    this.rentalRepository = rentalRepository;
    this.rentalAssembler = rentalAssembler;
  }

  public List<RentalDTO> getRentals(RentalQuery rentalQuery) {
    List<Rental> rentals = rentalRepository.fetchAll(rentalQuery);

    return rentalAssembler.toDto(rentals);
  }

  public RentalDTO getRental(String rentalId) {
    Rental rental = rentalRepository.fetch(rentalId);

    return rentalAssembler.toDto(rental);
  }
}
