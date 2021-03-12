package rental.infrastructure.utility;

import rental.infrastructure.exception.InfrastructureException;

public class InvalidCSVFileException extends InfrastructureException {

  private static final String CODE = "INVALID_CSV_FILE";
  private static final String MESSAGE = "CSV file cannot be parsed";

  public InvalidCSVFileException() {
    super(CODE, MESSAGE);
  }
}
