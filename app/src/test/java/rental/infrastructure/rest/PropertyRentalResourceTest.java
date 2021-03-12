package rental.infrastructure.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.application.RentalDTO;
import rental.application.RentalService;
import rental.domain.rental.RentalIdentifier;
import rental.domain.rental.RentalQuery;
import utility.RentalDTOBuilder;

public class PropertyRentalResourceTest {

  private static final String SOME_RENTAL_ID = new Faker().internet().uuid();
  private static final Integer SOME_MINIMUM_NUMBER_OF_BEDS = 3;
  private static final Integer SOME_MINIMUM_PRICE = 50;
  private static final Integer SOME_MAXIMUM_PRICE = 100;
  private static final String SOME_POSTAL_CODE_PATTERN = "G3A__4";
  private static final RentalDTO SOME_RENTAL_DTO = new RentalDTOBuilder().build();
  private static final List<RentalDTO> SOME_RENTALS_DTO = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_DTO, new RentalDTOBuilder().build()));

  private RentalService rentalService;
  private PropertyRentalResource propertyRentalResource;

  @BeforeEach
  public void setUp() {
    rentalService = mock(RentalService.class);
    when(rentalService.getRentals(any())).thenReturn(SOME_RENTALS_DTO);
    when(rentalService.getRental(RentalIdentifier.fromString(SOME_RENTAL_ID))).thenReturn(SOME_RENTAL_DTO);

    propertyRentalResource = new PropertyRentalResource(rentalService);
  }

  @Test
  public void whenGettingRentalsWithQueryParams_thenShouldGetRentalsFromServiceWithProperRentalQuery() {
    RentalQuery rentalQuery = new RentalQuery.RentalQueryBuilder().witMinNbBeds(SOME_MINIMUM_NUMBER_OF_BEDS)
        .withPostalCodePattern(SOME_POSTAL_CODE_PATTERN)
        .withPriceRange(SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE).build();

    propertyRentalResource
        .getRentals(SOME_MINIMUM_NUMBER_OF_BEDS, SOME_POSTAL_CODE_PATTERN, SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE);

    verify(rentalService).getRentals(rentalQuery);
  }

  @Test
  public void whenGettingRentalsWithQueryParams_thenShouldReturnThem() {
    List<MinimalPropertyRentalResponse> expectedResponse =
        SOME_RENTALS_DTO.stream().map(MinimalPropertyRentalResponse::new).collect(
            Collectors.toList());

    Response actualResponse =
        propertyRentalResource
            .getRentals(SOME_MINIMUM_NUMBER_OF_BEDS, SOME_POSTAL_CODE_PATTERN, SOME_MINIMUM_PRICE, SOME_MAXIMUM_PRICE);


    assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    assertEquals(expectedResponse, actualResponse.getEntity());
  }

  @Test
  public void whenGettingRentalWithID_thenShouldGetRentalWithIDFromService() {
    propertyRentalResource.getRental(SOME_RENTAL_ID);

    verify(rentalService).getRental(RentalIdentifier.fromString(SOME_RENTAL_ID));
  }

  @Test
  public void whenGettingRentalWithID_thenShouldReturnIt() {
    PropertyRentalResponse expectedResponse = new PropertyRentalResponse(SOME_RENTAL_DTO);

    Response actualResponse =
        propertyRentalResource.getRental(SOME_RENTAL_ID);


    assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    assertEquals(expectedResponse, actualResponse.getEntity());
  }
}
