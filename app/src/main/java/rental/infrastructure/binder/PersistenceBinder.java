package rental.infrastructure.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rental.domain.rental.RentalRepository;
import rental.infrastructure.persistence.CSVRentalRecordAssembler;
import rental.infrastructure.persistence.CSVRentalRepository;

public class PersistenceBinder extends AbstractBinder {

  @Override
  protected void configure() {
    bind(CSVRentalRepository.class).to(RentalRepository.class);
    bind(CSVRentalRecordAssembler.class).to(CSVRentalRecordAssembler.class);
  }
}
