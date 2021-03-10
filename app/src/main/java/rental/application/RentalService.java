package rental.application;

import java.util.List;
import javax.inject.Inject;
import rental.domain.Rental;
import rental.domain.RentalRepository;

public class RentalService {

  private final RentalRepository rentalRepository;
  private final RentalAssembler rentalAssembler;

  @Inject
  public RentalService(RentalRepository rentalRepository, RentalAssembler rentalAssembler) {
    this.rentalRepository = rentalRepository;
    this.rentalAssembler = rentalAssembler;
  }

  public List<RentalDTO> getAllRentals() {
    List<Rental> rentals =  rentalRepository.getAll();
    return rentalAssembler.toDto(rentals);
  }
}
