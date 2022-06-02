package asw.edipogram.enigmiseguiti.event;

import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Value("${asw.edipogram.channel.in}")
    private String channel;

    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    @KafkaListener(topics="${asw.edipogram.channel.in}")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        DomainEvent event = record.value();
        enigmiSeguitiService.onMessage(event);
    }
}
