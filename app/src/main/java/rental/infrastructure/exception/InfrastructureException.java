package rental.infrastructure.exception;

public class InfrastructureException extends RuntimeException {
  private final String code;
  private final String message;

  public InfrastructureException(String code, String message) {
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
