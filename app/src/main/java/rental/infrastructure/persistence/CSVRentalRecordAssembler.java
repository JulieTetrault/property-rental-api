package rental.infrastructure.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVRecord;
import rental.domain.rental.Rental;
import rental.domain.rental.RentalRating;

public class CSVRentalRecordAssembler {

  public Rental fromRecord(CSVRecord csvRentalRecord) {
    return new Rental(
        csvRentalRecord.get("id"),
        csvRentalRecord.get("city"),
        csvRentalRecord.get("postalcode"),
        new BigDecimal(csvRentalRecord.get("price")),
        Integer.parseInt(csvRentalRecord.get("nb_beds")),
        Integer.parseInt(csvRentalRecord.get("nb_baths")),
        csvRentalRecord.get("owner"),
        RentalRating.fromString(csvRentalRecord.get("rating")),
        csvRentalRecord.get("description")
    );
  }

  public List<Rental> fromRecord(List<CSVRecord> csvRentalRecords) {
    return csvRentalRecords.stream().map(this::fromRecord).collect(Collectors.toList());
  }
}
