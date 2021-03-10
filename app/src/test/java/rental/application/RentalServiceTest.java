package rental.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.domain.Rental;
import rental.domain.RentalRepository;
import rental.infrastructure.persistence.RentalBuilder;

public class RentalServiceTest {
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(new RentalBuilder().build(), new RentalBuilder().build()));
  private static final List<RentalDTO> SOME_RENTAL_DTOS = new ArrayList<>(
      Arrays.asList(new RentalDTOBuilder().build(), new RentalDTOBuilder().build()));

  private RentalRepository rentalRepository;
  private RentalAssembler rentalAssembler;
  private RentalService rentalService;

  @BeforeEach
  public void setUp() {
    rentalRepository = mock(RentalRepository.class);
    when(rentalRepository.getAll()).thenReturn(SOME_RENTALS);

    rentalAssembler = mock(RentalAssembler.class);
    when(rentalAssembler.toDto(SOME_RENTALS)).thenReturn(SOME_RENTAL_DTOS);

    rentalService = new RentalService(rentalRepository, rentalAssembler);
  }

  @Test
  public void whenGettingAllRentals_thenShouldGetAllRentalsFromRepository() {
    rentalService.getAllRentals();

    verify(rentalRepository).getAll();
  }

  @Test
  public void whenGettingAllRentals_thenShouldAssembleRentalDTOsFromRentals() {
    rentalService.getAllRentals();

    verify(rentalAssembler).toDto(SOME_RENTALS);
  }

  @Test
  public void whenGettingAllRentals_thenShouldReturnAllRentalDTOs() {
    List<RentalDTO> actualRentalDTOs = rentalService.getAllRentals();

    assertEquals(SOME_RENTAL_DTOS, actualRentalDTOs);
  }
}
