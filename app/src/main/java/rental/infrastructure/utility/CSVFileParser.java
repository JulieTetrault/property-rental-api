package rental.infrastructure.utility;

import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.glassfish.jersey.internal.guava.Lists;

public class CSVFileParser {

  public List<CSVRecord> parse(String filename) {
    try {
      InputStreamReader inputSteamReader = new InputStreamReader(
          this.getClass().getResourceAsStream("/" + filename));

      return Lists.newArrayList(CSVFormat.DEFAULT
          .withFirstRecordAsHeader()
          .parse(inputSteamReader));
    } catch (Exception e) {
      throw new InvalidCSVFileException();
    }
  }
}
