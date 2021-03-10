package rental.infrastructure.rest;

import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.application.RentalDTO;
import rental.application.RentalDTOBuilder;
import rental.application.RentalService;

public class PropertyRentalResourceTest {
  private static final String SOME_RENTAL_ID = "ID";
  private static final RentalDTO SOME_RENTAL_DTO_WITH_ID = new RentalDTOBuilder().build();
  private static final List<RentalDTO> SOME_RENTAL_DTOS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_DTO_WITH_ID, new RentalDTOBuilder().build()));

  private RentalService rentalService;
  private PropertyRentalResource propertyRentalResource;

  @BeforeEach
  public void setUp() {
    rentalService = mock(RentalService.class);
    when(rentalService.getAllRentals()).thenReturn(SOME_RENTAL_DTOS);
    when(rentalService.getRental(SOME_RENTAL_ID)).thenReturn(SOME_RENTAL_DTO_WITH_ID);

    propertyRentalResource = new PropertyRentalResource(rentalService);
  }

  @Test
  public void whenGettingRentals_thenShouldGetRentalsFromService() {
    propertyRentalResource.getRentals();

    verify(rentalService).getAllRentals();
  }

  @Test
  public void whenGettingRental_thenShouldGetRentalFromService() {
    propertyRentalResource.getRental(SOME_RENTAL_ID);

    verify(rentalService).getRental(SOME_RENTAL_ID);
  }
}
