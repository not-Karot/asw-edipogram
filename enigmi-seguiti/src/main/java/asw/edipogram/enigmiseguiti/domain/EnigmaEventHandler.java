package asw.edipogram.enigmiseguiti.domain;

import asw.edipogram.enigmiseguiti.event.DomainEvent;
import asw.edipogram.enigmiseguiti.event.CreateEnigmaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnigmaEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(EnigmaEventHandler.class.toString());

    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    public void onEvent(DomainEvent event)
    {
        if(event.getClass().equals(CreateEnigmaEvent.class)){
            CreateEnigmaEvent e = (CreateEnigmaEvent) event;
            this.createEnigma(e);
        } else {
            logger.info("Problemi con evento: " + event);
        }

    }

    /* Crea un enigma */
    public void createEnigma(CreateEnigmaEvent event)
    {
        Enigma enigma = new Enigma(
                event.getId(),
                event.getAutore(),
                event.getTipo(),
                event.getTipoSpecifico(),
                event.getTitolo(),
                event.getTesto()
        );
        enigmiSeguitiService.addEnigma(enigma);
        logger.info("Creato l'enigma: " + event);
    }

}
