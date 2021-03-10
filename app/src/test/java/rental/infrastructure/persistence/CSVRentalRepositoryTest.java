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

  private static final List<CSVRecord> SOME_CSV_RENTAL_RECORDS = new ArrayList<>(
      Arrays.asList(new CSVRecordBuilder().build(), new CSVRecordBuilder().build()));
  private static final List<Rental> SOME_RENTALS = new ArrayList<>(
      Arrays.asList(new RentalBuilder().build(), new RentalBuilder().build()));

  private CSVRentalRecordAssembler csvRentalRecordAssembler;
  private CSVRentalRepository cvsRentalRepository;

  @BeforeEach
  public void setUp() {
    CSVFileParser csvFileParser = mock(CSVFileParser.class);
    when(csvFileParser.parse(anyString())).thenReturn(SOME_CSV_RENTAL_RECORDS);

    csvRentalRecordAssembler = mock(CSVRentalRecordAssembler.class);
    when(csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORDS)).thenReturn(SOME_RENTALS);

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
}


