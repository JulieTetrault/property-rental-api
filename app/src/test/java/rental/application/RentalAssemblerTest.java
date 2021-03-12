package rental.application;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.domain.rental.Rental;
import rental.infrastructure.persistence.RentalBuilder;

public class RentalAssemblerTest {

  private static final Rental SOME_RENTAL = new RentalBuilder().build();
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL, new RentalBuilder().build()));

  private RentalAssembler rentalAssembler;

  @BeforeEach
  public void setUp() {
    rentalAssembler = new RentalAssembler();
  }

  @Test
  public void whenAssemblingRentalDTOFromRental_thenShouldReturnCorrespondingRentalDTO() {
    RentalDTO rentalDTO = rentalAssembler.toDto(SOME_RENTAL);

    assertRentalDTOCorrectlyAssembled(SOME_RENTAL, rentalDTO);
  }

  private void assertRentalDTOCorrectlyAssembled(Rental rental, RentalDTO rentalDTO) {
    assertEquals(rental.getId(), rentalDTO.id);
    assertEquals(rental.getCity(), rentalDTO.city);
    assertEquals(rental.getPostalCode(), rentalDTO.postalCode);
    assertEquals(rental.getPrice().intValue(), rentalDTO.price);
    assertEquals(rental.getNbBeds(), rentalDTO.nbBeds);
    assertEquals(rental.getNbBaths(), rentalDTO.nbBaths);
    assertEquals(rental.getOwner(), rentalDTO.owner);
    assertEquals(rental.getRating(), rentalDTO.rating);
    assertEquals(rental.getDescription(), rentalDTO.description);
  }

  @Test
  public void whenAssemblingRentalsDTOFromRentals_thenShouldReturnCorrespondingRentalsDTO() {
    List<RentalDTO> rentalsDTO = rentalAssembler.toDto(SOME_RENTALS);

    assertRentalsDTOCorrectlyAssembled(rentalsDTO);
  }

  private void assertRentalsDTOCorrectlyAssembled(List<RentalDTO> rentalsDTO) {
    for (int index = 0; index < rentalsDTO.size(); index++) {
      assertRentalDTOCorrectlyAssembled(RentalAssemblerTest.SOME_RENTALS.get(index), rentalsDTO.get(index));
    }
  }
}
