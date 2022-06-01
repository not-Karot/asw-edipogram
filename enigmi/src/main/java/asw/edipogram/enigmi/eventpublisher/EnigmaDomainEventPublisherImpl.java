package asw.edipogram.enigmi.eventpublisher;

import asw.edipogram.enigmi.domain.EnigmaDomainEventPublisher;
import asw.edipogram.enigmi.event.DomainEvent;
import asw.edipogram.enigmi.event.EnigmaServiceEventChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnigmaDomainEventPublisherImpl implements EnigmaDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private String channel = EnigmaServiceEventChannel.channel;

    public void publish(DomainEvent event)
    {
        template.send(channel, event);
    }

}
