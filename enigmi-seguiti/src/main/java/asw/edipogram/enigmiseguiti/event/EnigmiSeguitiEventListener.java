package asw.edipogram.enigmiseguiti.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnigmiSeguitiEventListener {

    @Autowired
    private EnigmiSeguitiEventHandler enigmiSeguitiEventHandler;

    /* Variabile apparentemente inutile che forse si può togliere
    *  perché i canali li inserisco direttamente nell'annotazione */
    private final String channel = EnigmiEventChannel.channel;

    @KafkaListener(topics = {EnigmiEventChannel.channel, ConnessioniEventChannel.channel}, groupId = "Group1")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception
    {
        DomainEvent event = record.value();
        enigmiSeguitiEventHandler.onEvent(event);
    }

}
