package asw.edipogram.connessioni.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class ConnessioniDomainEventPublisherImpl implements ConnessioniDomainEventPublisher {

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    @Value("${asw.edipogram.channel.out}")
    private String channel;

    @Override
    public void publish(DomainEvent event) {

        template.send(channel, event);

    }
}
