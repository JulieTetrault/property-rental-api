package rental.domain.rental;

import java.util.List;


public interface RentalRepository {
  List<Rental> getAll(RentalQuery rentalQuery);

  Rental get(String rentalId);
}

