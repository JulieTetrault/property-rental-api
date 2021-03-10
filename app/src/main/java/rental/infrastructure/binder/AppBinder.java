package rental.infrastructure.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class AppBinder extends AbstractBinder {

  @Override
  protected void configure() {
    install(new ApplicationBinder());
    install(new PersistenceBinder());
    install(new UtilityBinder());
  }
}
