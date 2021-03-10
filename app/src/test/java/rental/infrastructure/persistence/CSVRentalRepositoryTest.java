package rental.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.domain.Rental;
import rental.infrastructure.utility.CSVFileParser;


public class CSVRentalRepositoryTest {

  private static final String SOME_RENTAL_ID = "ID";
  private static final CSVRecord SOME_CSV_RENTAL_RECORD_WITH_ID = new CSVRecordBuilder().withId(SOME_RENTAL_ID).build();
  private static final List<CSVRecord> SOME_CSV_RENTAL_RECORDS = new ArrayList<>(
      Arrays.asList(SOME_CSV_RENTAL_RECORD_WITH_ID, new CSVRecordBuilder().build()));
  private static final Rental SOME_RENTAL_WITH_ID = new RentalBuilder().build();
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(SOME_RENTAL_WITH_ID, new RentalBuilder().build()));

  private CSVRentalRecordAssembler csvRentalRecordAssembler;
  private CSVRentalRepository cvsRentalRepository;

  @BeforeEach
  public void setUp() {
    CSVFileParser csvFileParser = mock(CSVFileParser.class);
    when(csvFileParser.parse(anyString())).thenReturn(SOME_CSV_RENTAL_RECORDS);

    csvRentalRecordAssembler = mock(CSVRentalRecordAssembler.class);
    when(csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORDS)).thenReturn(SOME_RENTALS);
    when(csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORD_WITH_ID)).thenReturn(SOME_RENTAL_WITH_ID);

    cvsRentalRepository = new CSVRentalRepository(csvRentalRecordAssembler, csvFileParser);
  }

  @Test
  public void whenGettingAllRentals_thenShouldAssembleRentalsFromCSVRentalRecords() {
    cvsRentalRepository.getAll();

    verify(csvRentalRecordAssembler).fromRecord(SOME_CSV_RENTAL_RECORDS);
  }

  @Test
  public void whenGettingAllRentals_thenShouldReturnAllRentals() {
    List<Rental> actualRentals = cvsRentalRepository.getAll();

    assertEquals(SOME_RENTALS, actualRentals);
  }

  @Test
  public void givenExistingRental_whenGettingRental_thenShouldAssembleRentalFromCorrespondingCSVRentalRecord() {
    cvsRentalRepository.get(SOME_RENTAL_ID);

    verify(csvRentalRecordAssembler).fromRecord(SOME_CSV_RENTAL_RECORD_WITH_ID);
  }

  @Test
  public void whenGettingRental_thenShouldReturnRental() {
    Rental actualRental = cvsRentalRepository.get(SOME_RENTAL_ID);

    assertEquals(SOME_RENTAL_WITH_ID, actualRental);
  }
}


