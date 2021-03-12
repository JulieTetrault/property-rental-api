package rental.domain.exception;

public class DomainException extends RuntimeException {

  private final String code;
  private final String message;

  public DomainException(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
