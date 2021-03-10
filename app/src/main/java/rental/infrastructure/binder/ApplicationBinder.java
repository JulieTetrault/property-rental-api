package rental.infrastructure.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rental.application.RentalService;

public class ApplicationBinder extends AbstractBinder {

  @Override
  protected void configure() {
    bind(RentalService.class).to(RentalService.class);
  }
}
