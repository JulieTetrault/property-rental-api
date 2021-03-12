package rental.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.commons.csv.CSVRecord;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalIdentifier;
import rental.domain.rental.RentalQuery;
import rental.domain.rental.RentalRepository;
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
  public List<Rental> fetchAll(RentalQuery rentalQuery) {
    List<Rental> rentals = csvRentalRecordAssembler.fromRecord(csvRentalRecords);

    return rentals.stream().filter(rentalQuery::apply).collect(Collectors.toList());
  }

  @Override
  public Rental fetch(RentalIdentifier rentalId) {
    Optional<CSVRecord> csvRentalRecord =
        csvRentalRecords.stream().filter(record -> RentalIdentifier.from(record.get("id"))
            .equals(rentalId)).findFirst();

    if (csvRentalRecord.isPresent()) {
      return csvRentalRecordAssembler.fromRecord(csvRentalRecord.get());
    }

    throw new RentalNotFoundException(rentalId);
  }
}
