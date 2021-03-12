package rental.domain.rental;

import java.util.List;


public interface RentalRepository {
  List<Rental> fetchAll(RentalQuery rentalQuery);

  Rental fetch(String rentalId);
}
