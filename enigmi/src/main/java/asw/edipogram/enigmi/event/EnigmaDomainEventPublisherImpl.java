package asw.edipogram.enigmi.event;

import asw.edipogram.enigmi.event.EnigmaDomainEventPublisher;
import asw.edipogram.enigmi.event.DomainEvent;
import asw.edipogram.enigmi.event.EnigmaServiceEventChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnigmaDomainEventPublisherImpl implements EnigmaDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private String channel = EnigmaServiceEventChannel.channel;

    @Override
    public void publish(DomainEvent event)
    {
        template.send(channel, event);
    }

}
