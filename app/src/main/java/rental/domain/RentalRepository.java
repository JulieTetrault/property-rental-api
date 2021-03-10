package rental.domain;

import java.util.List;

public interface RentalRepository {
  List<Rental> getAll();
}

