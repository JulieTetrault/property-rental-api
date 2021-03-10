package rental.infrastructure.persistence;

import java.io.IOException;
import java.io.StringReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVRecordBuilder {

  private static final String[] SOME_CSV_RECORD_VALUES = new String[] {"firstValue", "secondValue", "thirdValue"};

  private CSVRecord csvRecord;

  public CSVRecordBuilder() {
    final String rowData = String.join(",", SOME_CSV_RECORD_VALUES);

    try (final CSVParser parser = CSVFormat.DEFAULT.parse(new StringReader(rowData))) {
      this.csvRecord = parser.iterator().next();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public CSVRecord build() {
    return csvRecord;
  }
}


