package rental.infrastructure.persistence;

import java.util.List;
import javax.inject.Inject;
import org.apache.commons.csv.CSVRecord;
import rental.domain.Rental;
import rental.domain.RentalRepository;
import rental.infrastructure.utility.CSVFileParser;

public class CSVRentalRepository implements RentalRepository {
  public static final String FILENAME = "rentals.csv";

  private final CSVRentalRecordAssembler csvRentalRecordAssembler;
  private final List<CSVRecord> csvRentalRecords;

  @Inject
  public CSVRentalRepository(CSVRentalRecordAssembler csvRentalRecordAssembler, CSVFileParser csvFileParser) {
    this.csvRentalRecordAssembler = csvRentalRecordAssembler;
    this.csvRentalRecords = csvFileParser.parse(FILENAME);
  }

  @Override
  public List<Rental> getAll() {
    return csvRentalRecordAssembler.fromRecord(csvRentalRecords);
  }

  @Override
  public Rental get(String rentalId) {
    CSVRecord csvRentalRecord =
        csvRentalRecords.stream().filter(record -> record.get("id").equals(rentalId)).findFirst().get();

    return csvRentalRecordAssembler.fromRecord(csvRentalRecord);
  }
}

