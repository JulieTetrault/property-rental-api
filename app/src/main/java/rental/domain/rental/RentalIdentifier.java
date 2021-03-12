package rental.domain.rental;

import java.util.Objects;
import java.util.UUID;

public class RentalIdentifier {

  private final UUID identifier;

  private RentalIdentifier(String identifier) {
    this.identifier = UUID.fromString(identifier);
  }

  public static RentalIdentifier from(String identifier) {
    return new RentalIdentifier(identifier);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof RentalIdentifier)) {
      return false;
    }

    RentalIdentifier that = (RentalIdentifier) o;
    return Objects.equals(this.identifier, that.identifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifier);
  }

  @Override
  public String toString() {
    return identifier.toString();
  }
}
