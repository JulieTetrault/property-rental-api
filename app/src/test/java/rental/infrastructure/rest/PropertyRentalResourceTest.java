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
import rental.domain.rental.RentalQuery;

public class PropertyRentalResourceTest {
  private static final String SOME_RENTAL_ID = "ID";
  private static final Integer SOME_MIN_NB_NUMBER = 3;
  private static final Integer SOME_MIN_PRICE = 10;
  private static final Integer SOME_MAX_PRICE = 50;
  private static final String SOME_POSTAL_CODE = "postalcode";
  private static final RentalDTO SOME_RENTAL_DTO_WITH_ID = new RentalDTOBuilder().build();
  private static final List<RentalDTO> SOME_RENTAL_DTOS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_DTO_WITH_ID, new RentalDTOBuilder().build()));

  private RentalService rentalService;
  private PropertyRentalResource propertyRentalResource;

  @BeforeEach
  public void setUp() {
    rentalService = mock(RentalService.class);
    when(rentalService.getAllRentals(any())).thenReturn(SOME_RENTAL_DTOS);
    when(rentalService.getRental(SOME_RENTAL_ID)).thenReturn(SOME_RENTAL_DTO_WITH_ID);

    propertyRentalResource = new PropertyRentalResource(rentalService);
  }

  @Test
  public void whenGettingRentals_thenShouldGetRentalsFromService() {
    propertyRentalResource.getRentals(SOME_MIN_NB_NUMBER, SOME_POSTAL_CODE, SOME_MIN_PRICE, SOME_MAX_PRICE);

    RentalQuery rentalQuery = new RentalQuery.RentalQueryBuilder().witMinNbBeds(SOME_MIN_NB_NUMBER)
        .withPriceRange(SOME_MIN_PRICE, SOME_MAX_PRICE).build();

    verify(rentalService).getAllRentals(rentalQuery);
  }

  @Test
  public void whenGettingRental_thenShouldGetRentalFromService() {
    propertyRentalResource.getRental(SOME_RENTAL_ID);

    verify(rentalService).getRental(SOME_RENTAL_ID);
  }
}
