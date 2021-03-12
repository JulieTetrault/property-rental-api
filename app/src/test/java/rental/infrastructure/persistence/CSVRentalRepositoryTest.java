package rental.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalIdentifier;
import rental.domain.rental.RentalQuery;
import rental.infrastructure.utility.CSVFileParser;
import utility.CSVRecordBuilder;
import utility.RentalBuilder;

public class CSVRentalRepositoryTest {

  private static final String SOME_RENTAL_ID = new Faker().internet().uuid();
  private static final CSVRecord SOME_CSV_RENTAL_RECORD = new CSVRecordBuilder().withId(SOME_RENTAL_ID).build();
  private static final List<CSVRecord> SOME_CSV_RENTAL_RECORDS = new ArrayList<>(
      Arrays.asList(SOME_CSV_RENTAL_RECORD, new CSVRecordBuilder().build()));
  private static final Rental SOME_RENTAL = new RentalBuilder().build();
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL, new RentalBuilder().build()));
  private static final RentalQuery SOME_RENTAL_QUERY = new RentalQuery.RentalQueryBuilder().build();

  private CSVRentalRecordAssembler csvRentalRecordAssembler;
  private CSVRentalRepository cvsRentalRepository;

  @BeforeEach
  public void setUp() {
    CSVFileParser csvFileParser = mock(CSVFileParser.class);
    when(csvFileParser.parse(anyString())).thenReturn(SOME_CSV_RENTAL_RECORDS);

    csvRentalRecordAssembler = mock(CSVRentalRecordAssembler.class);
    when(csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORDS)).thenReturn(SOME_RENTALS);
    when(csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORD)).thenReturn(SOME_RENTAL);

    cvsRentalRepository = new CSVRentalRepository(csvRentalRecordAssembler, csvFileParser);
  }

  @Test
  public void whenFetchingAllRentalsWithRentalQuery_thenShouldAssembleRentalsAndReturnThem() {
    List<Rental> actualRentals = cvsRentalRepository.fetchAll(SOME_RENTAL_QUERY);

    verify(csvRentalRecordAssembler).fromRecord(SOME_CSV_RENTAL_RECORDS);
    assertEquals(SOME_RENTALS, actualRentals);
  }

  @Test
  public void givenExistingRentalWithID_whenFetchingRental_thenShouldAssembleRentalAndReturnIt() {
    Rental actualRental = cvsRentalRepository.fetch(RentalIdentifier.fromString(SOME_RENTAL_ID));

    verify(csvRentalRecordAssembler).fromRecord(SOME_CSV_RENTAL_RECORD);
    assertEquals(SOME_RENTAL, actualRental);
  }

  @Test
  public void givenNonExistingRentalWithID_whenFetchingRental_thenShouldThrowRentalNotFoundException() {
    assertThrows(RentalNotFoundException.class, () -> {
      cvsRentalRepository.fetch(RentalIdentifier.fromString(new Faker().internet().uuid()));
    });
  }
}


