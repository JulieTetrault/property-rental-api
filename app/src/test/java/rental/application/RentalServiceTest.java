package rental.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalQuery;
import rental.domain.rental.RentalRepository;
import rental.infrastructure.persistence.RentalBuilder;
import utility.RentalDTOBuilder;

public class RentalServiceTest {

  private static final Rental SOME_RENTAL = new RentalBuilder().build();
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL, new RentalBuilder().build()));
  private static final RentalDTO SOME_RENTAL_DTO = new RentalDTOBuilder().build();
  private static final List<RentalDTO> SOME_RENTALS_DTO = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_DTO, new RentalDTOBuilder().build()));
  private static final RentalQuery SOME_RENTAL_QUERY = new RentalQuery.RentalQueryBuilder().build();

  private RentalRepository rentalRepository;
  private RentalAssembler rentalAssembler;
  private RentalService rentalService;

  @BeforeEach
  public void setUp() {
    rentalRepository = mock(RentalRepository.class);
    when(rentalRepository.fetchAll(SOME_RENTAL_QUERY)).thenReturn(SOME_RENTALS);
    when(rentalRepository.fetch(SOME_RENTAL.getId())).thenReturn(SOME_RENTAL);

    rentalAssembler = mock(RentalAssembler.class);
    when(rentalAssembler.toDto(SOME_RENTALS)).thenReturn(SOME_RENTALS_DTO);
    when(rentalAssembler.toDto(SOME_RENTAL)).thenReturn(SOME_RENTAL_DTO);

    rentalService = new RentalService(rentalRepository, rentalAssembler);
  }

  @Test
  public void whenGettingRentalsMatchingRentalQuery_thenShouldFetchMatchingRentalsFromRepository() {
    rentalService.getRentals(SOME_RENTAL_QUERY);

    verify(rentalRepository).fetchAll(SOME_RENTAL_QUERY);
  }

  @Test
  public void whenGettingRentalsMatchingRentalQuery_thenShouldAssembleMatchingRentalsDTOAndReturnThem() {
    List<RentalDTO> actualRentalsDTO = rentalService.getRentals(SOME_RENTAL_QUERY);

    verify(rentalAssembler).toDto(SOME_RENTALS);
    assertEquals(SOME_RENTALS_DTO, actualRentalsDTO);
  }

  @Test
  public void whenGettingRentalWithID_thenShouldFetchRentalWithIDFromRepository() {
    rentalService.getRental(SOME_RENTAL.getId());

    verify(rentalRepository).fetch(SOME_RENTAL.getId());
  }

  @Test
  public void whenGettingRentalWithID_thenShouldAssembleRentalDTOAndReturnIt() {
    RentalDTO actualRentalDTO = rentalService.getRental(SOME_RENTAL.getId());

    verify(rentalAssembler).toDto(SOME_RENTAL);
    assertEquals(SOME_RENTAL_DTO, actualRentalDTO);
  }
}
