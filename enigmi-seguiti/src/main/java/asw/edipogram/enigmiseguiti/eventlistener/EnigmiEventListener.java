package asw.edipogram.enigmiseguiti.eventlistener;

import asw.edipogram.enigmiseguiti.domain.EnigmaEventHandler;
import asw.edipogram.enigmiseguiti.event.DomainEvent;
import asw.edipogram.enigmiseguiti.event.EnigmaServiceEventChannel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmiEventListener {

    @Autowired
    private EnigmaEventHandler enigmaEventHandler;

    private final String channel = EnigmaServiceEventChannel.channel;

    @KafkaListener(topics = channel, groupId = "Group1")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception
    {
        DomainEvent event = record.value();
        enigmaEventHandler.onEvent(event);
    }

}
