package asw.edipogram.enigmi.domain;

import asw.edipogram.enigmi.event.DomainEvent;

public interface EnigmaDomainEventPublisher {

    public void publish(DomainEvent event);

}
