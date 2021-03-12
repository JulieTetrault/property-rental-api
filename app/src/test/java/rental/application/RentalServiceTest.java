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

public class RentalServiceTest {

  private static final String SOME_RENTAL_ID = "ID";
  private static final Rental SOME_RENTAL_WITH_ID = new RentalBuilder().build();
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_WITH_ID, new RentalBuilder().build()));
  private static final RentalDTO SOME_RENTAL_DTO_WITH_ID = new RentalDTOBuilder().build();
  private static final List<RentalDTO> SOME_RENTAL_DTOS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_DTO_WITH_ID, new RentalDTOBuilder().build()));
  private static final RentalQuery SOME_RENTAL_QUERY = new RentalQuery.RentalQueryBuilder().build();

  private RentalRepository rentalRepository;
  private RentalAssembler rentalAssembler;
  private RentalService rentalService;

  @BeforeEach
  public void setUp() {
    rentalRepository = mock(RentalRepository.class);
    when(rentalRepository.getAll(SOME_RENTAL_QUERY)).thenReturn(SOME_RENTALS);
    when(rentalRepository.get(SOME_RENTAL_ID)).thenReturn(SOME_RENTAL_WITH_ID);

    rentalAssembler = mock(RentalAssembler.class);
    when(rentalAssembler.toDto(SOME_RENTALS)).thenReturn(SOME_RENTAL_DTOS);
    when(rentalAssembler.toDto(SOME_RENTAL_WITH_ID)).thenReturn(SOME_RENTAL_DTO_WITH_ID);

    rentalService = new RentalService(rentalRepository, rentalAssembler);
  }

  @Test
  public void whenGettingAllRentals_thenShouldGetAllRentalsFromRepository() {
    rentalService.getAllRentals(SOME_RENTAL_QUERY);

    verify(rentalRepository).getAll(SOME_RENTAL_QUERY);
  }

  @Test
  public void whenGettingAllRentals_thenShouldAssembleRentalDTOsFromRentals() {
    rentalService.getAllRentals(SOME_RENTAL_QUERY);

    verify(rentalAssembler).toDto(SOME_RENTALS);
  }

  @Test
  public void whenGettingAllRentals_thenShouldReturnAllRentalDTOs() {
    List<RentalDTO> actualRentalDTOs = rentalService.getAllRentals(SOME_RENTAL_QUERY);

    assertEquals(SOME_RENTAL_DTOS, actualRentalDTOs);
  }

  @Test
  public void whenGettingRental_thenShouldAssembleRentalDTOFromRental() {
    rentalService.getRental(SOME_RENTAL_ID);

    verify(rentalAssembler).toDto(SOME_RENTAL_WITH_ID);
  }

  @Test
  public void whenGettingRental_thenShouldReturnRentalDTO() {
    RentalDTO actualRentalDTO = rentalService.getRental(SOME_RENTAL_ID);

    assertEquals(SOME_RENTAL_DTO_WITH_ID, actualRentalDTO);
  }
}
