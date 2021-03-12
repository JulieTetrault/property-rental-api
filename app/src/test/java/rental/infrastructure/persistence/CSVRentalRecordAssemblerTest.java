package rental.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalRating;
import utility.CSVRecordBuilder;

public class CSVRentalRecordAssemblerTest {

  private static final CSVRecord SOME_CSV_RENTAL_RECORD = new CSVRecordBuilder().build();
  private static final List<CSVRecord> SOME_CSV_RENTAL_RECORDS =
      new ArrayList<>(Arrays.asList(SOME_CSV_RENTAL_RECORD, new CSVRecordBuilder().build()));

  private CSVRentalRecordAssembler csvRentalRecordAssembler;

  @BeforeEach
  public void setUp() {
    csvRentalRecordAssembler = new CSVRentalRecordAssembler();
  }

  @Test
  public void whenAssemblingRentalFromCSVRentalRecord_thenShouldReturnCorrespondingRental() {
    Rental rental = csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORD);

    assertRentalCorrectlyAssembled(SOME_CSV_RENTAL_RECORD, rental);
  }

  private void assertRentalCorrectlyAssembled(CSVRecord csvRentalRecord, Rental rental) {
    assertEquals(csvRentalRecord.get("id"), rental.getId());
    assertEquals(csvRentalRecord.get("city"), rental.getCity());
    assertEquals(csvRentalRecord.get("postalcode"), rental.getPostalCode());
    assertEquals(new BigDecimal(csvRentalRecord.get("price")), rental.getPrice());
    assertEquals(Integer.parseInt(csvRentalRecord.get("nb_beds")), rental.getNbBeds());
    assertEquals(Integer.parseInt(csvRentalRecord.get("nb_baths")), rental.getNbBaths());
    assertEquals(csvRentalRecord.get("owner"), rental.getOwner());
    assertEquals(RentalRating.fromString(csvRentalRecord.get("rating")), rental.getRating());
    assertEquals(csvRentalRecord.get("description"), rental.getDescription());
  }

  @Test
  public void whenAssemblingRentalFromCSVRentalRecords_thenShouldReturnCorrespondingRentals() {
    List<Rental> rentals = csvRentalRecordAssembler.fromRecord(SOME_CSV_RENTAL_RECORDS);

    assertRentalsCorrectlyAssembled(rentals);
  }

  private void assertRentalsCorrectlyAssembled(List<Rental> rentals) {
    for (int index = 0; index < rentals.size(); index++) {
      assertRentalCorrectlyAssembled(SOME_CSV_RENTAL_RECORDS.get(index), rentals.get(index));
    }
  }
}
