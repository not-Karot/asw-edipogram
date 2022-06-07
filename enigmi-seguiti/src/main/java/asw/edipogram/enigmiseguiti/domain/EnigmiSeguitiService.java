package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.*;

@Service
public class EnigmiSeguitiService {

    private final Logger logger = Logger.getLogger(EnigmiSeguitiService.class.toString());

    @Autowired
    private ConnessioniService connessioniService;

    @Autowired
    private EnigmiService enigmiService;

    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Autowired
    private EnigmiRepository enigmiRepository;

    @Autowired
    private EnigmiSeguitiRepository enigmiSeguitiRepository;

    /* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */
    public Collection<Enigma> getEnigmiSeguiti(String utente) {
        Collection<Enigma> enigmiSeguiti = new TreeSet<>();
        Collection<Connessione> connessioni = connessioniService.getConnessioniByUtente(utente);
        Collection<String> tipiSeguiti =
                connessioni
                        .stream()
                        .map(c -> c.getTipo())
                        .collect(Collectors.toSet());
        if (tipiSeguiti.size() > 0) {
            Collection<Enigma> enigmi = enigmiService.getEnigmiByTipi(tipiSeguiti);
            enigmiSeguiti.addAll(enigmi);
        }
        return enigmiSeguiti;
    }

    /* Aggiunge un enigma al db */
    public void addEnigma(Enigma enigma) {
        enigmiRepository.save(enigma);
    }

    /* Aggiunge una connessione al db */
    public void addConnessione(Connessione connessione) {
        connessioniRepository.save(connessione);
    }

    /* Aggiunge gli enigmi seguiti al db alla ricezione di EnigmaCreatedEvent */
    public void addEnigmiSeguitiByEnigma(Enigma enigma) {
        String tipo = enigma.getTipo();
        Collection<Connessione> connessioni = connessioniRepository.findByTipo(tipo);
        connessioni.stream()
                .map(Connessione::getUtente)
                .forEach(u -> {
                    EnigmiSeguiti e = new EnigmiSeguiti(
                            u,
                            enigma.getId(),
                            enigma.getAutore(),
                            enigma.getTipo(),
                            enigma.getTipoSpecifico(),
                            enigma.getTitolo(),
                            enigma.getTesto()
                    );
                    enigmiSeguitiRepository.save(e);
                });
        logger.info(connessioni.toString());
    }

    /* Aggiunge gli enigmi seguiti al db alla ricezione di ConnessioneCreatedEvent */
    public void addEnigmiSeguitiByConnessione(Connessione connessione) {
        String tipo = connessione.getTipo();
        Collection<Enigma> enigmi = enigmiRepository.findByTipo(tipo);
        enigmi.forEach(e -> {
            EnigmiSeguiti enigmiSeguiti = new EnigmiSeguiti(
                    connessione.getUtente(),
                    e.getId(),
                    e.getAutore(),
                    e.getTipo(),
                    e.getTipoSpecifico(),
                    e.getTitolo(),
                    e.getTesto()
            );
            enigmiSeguitiRepository.save(enigmiSeguiti);
        });
        logger.info(enigmi.toString());
    }
}
