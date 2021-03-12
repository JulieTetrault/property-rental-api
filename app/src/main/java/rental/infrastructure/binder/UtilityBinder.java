package rental.infrastructure.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rental.infrastructure.utility.CSVFileParser;

public class UtilityBinder extends AbstractBinder {

  @Override
  protected void configure() {
    bind(CSVFileParser.class).to(CSVFileParser.class);
  }
}
