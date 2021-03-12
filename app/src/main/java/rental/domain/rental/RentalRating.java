package rental.domain.rental;

public enum RentalRating {
  ONE,
  TWO,
  THREE,
  FOUR,
  FIVE;

  private static final RentalRating[] allRentalRatingValues = values();

  public static RentalRating fromString(String rentalRating) {
    try {
      return allRentalRatingValues[Integer.parseInt(rentalRating) - 1];
    } catch (Exception e) {
      throw new InvalidRentalRatingException(rentalRating);
    }
  }

  public Integer toInt() {
    return this.ordinal() + 1;
  }
}
