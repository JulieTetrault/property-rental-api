package rental.infrastructure.rest.mappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;

public class ExceptionResponse {

  public final String code;
  public final String message;

  @JsonCreator
  public ExceptionResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ExceptionResponse)) {
      return false;
    }

    ExceptionResponse that = (ExceptionResponse) o;
    return Objects.equals(this.code, that.code) && Objects.equals(this.message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }
}
