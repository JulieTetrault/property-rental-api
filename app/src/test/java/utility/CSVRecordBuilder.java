package utility;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVRecordBuilder {

  private static final String SOME_ID = "id";
  private static final String SOME_CITY = "city";
  private static final String SOME_POSTAL_CODE = "postalCode";
  private static final String SOME_PRICE = "30";
  private static final String SOME_NB_BEDS = "4";
  private static final String SOME_NB_BATHS = "3";
  private static final String SOME_OWNER = "owner";
  private static final String SOME_RATING = "2";
  private static final String SOME_DESCRIPTION = "description";

  Map<String, String> csvRecordValues = new HashMap<>();

  public CSVRecordBuilder() {
    csvRecordValues.put("id", SOME_ID);
    csvRecordValues.put("city", SOME_CITY);
    csvRecordValues.put("postalcode", SOME_POSTAL_CODE);
    csvRecordValues.put("price", SOME_PRICE);
    csvRecordValues.put("nb_beds", SOME_NB_BEDS);
    csvRecordValues.put("nb_baths", SOME_NB_BATHS);
    csvRecordValues.put("owner", SOME_OWNER);
    csvRecordValues.put("rating", SOME_RATING);
    csvRecordValues.put("description", SOME_DESCRIPTION);
  }

  public CSVRecordBuilder withId(String id) {
    csvRecordValues.put("id", id);
    return this;
  }

  public CSVRecord build() {
    String rowData = String.join(",", csvRecordValues.values());
    CSVParser parser = null;
    try {
      parser = new CSVParser(new StringReader(rowData), CSVFormat.DEFAULT
          .withHeader(csvRecordValues.keySet().toArray(new String[0])));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return parser.iterator().next();
  }
}


