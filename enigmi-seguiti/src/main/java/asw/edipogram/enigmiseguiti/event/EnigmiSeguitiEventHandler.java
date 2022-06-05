package asw.edipogram.enigmiseguiti.event;

import asw.edipogram.enigmiseguiti.domain.Connessione;
import asw.edipogram.enigmiseguiti.domain.Enigma;
import asw.edipogram.enigmiseguiti.domain.EnigmiSeguitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnigmiSeguitiEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(EnigmiSeguitiEventHandler.class.toString());

    @Autowired
    private EnigmiSeguitiService enigmiSeguitiService;

    public void onEvent(DomainEvent event)
    {
        if(event.getClass().equals(EnigmaCreatedEvent.class)){
            EnigmaCreatedEvent e = (EnigmaCreatedEvent) event;
            this.createEnigma(e);
        } else if(event.getClass().equals(ConnessioneCreatedEvent.class)){
            ConnessioneCreatedEvent e = (ConnessioneCreatedEvent) event;
            this.createConnessione(e);
        } else {
            logger.info("Problema con evento di tipo " + event);
        }

    }

    /* Crea un enigma */
    public void createEnigma(EnigmaCreatedEvent event) {
        Enigma enigma = new Enigma(
                event.getId(),
                event.getAutore(),
                event.getTipo(),
                event.getTipoSpecifico(),
                event.getTitolo(),
                event.getTesto()
        );
        enigmiSeguitiService.addEnigma(enigma);
        logger.info("Creato l'enigma: " + enigma.toString());
    }

    /* Crea una connessione */
    private void createConnessione(ConnessioneCreatedEvent event) {
        Connessione connessione = new Connessione(
                event.getId(),
                event.getUtente(),
                event.getTipo()
        );
        enigmiSeguitiService.addConnessione(connessione);
        logger.info("Creata la connessione: " + connessione.toString());
    }
}
