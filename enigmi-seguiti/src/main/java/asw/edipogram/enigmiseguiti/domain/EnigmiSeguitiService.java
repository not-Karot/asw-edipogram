package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*;

import asw.edipogram.enigmiseguiti.event.*;

@Service 
public class EnigmiSeguitiService {

	@Autowired 
	private ConnessioniService connessioniService;

	@Autowired 
	private EnigmiService enigmiService;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<Enigma> getEnigmiSeguiti(String utente) {
		Collection<Enigma> enigmiSeguiti = new TreeSet<>(); 
		Collection<Connessione> connessioni = connessioniService.getConnessioniByUtente(utente); 
		Collection<String> tipiSeguiti = 
			connessioni
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet()); 
		if (tipiSeguiti.size()>0) {
			Collection<Enigma> enigmi = enigmiService.getEnigmiByTipi(tipiSeguiti);
			enigmiSeguiti.addAll(enigmi); 
		}
		return enigmiSeguiti; 
	}

	public void onMessage(DomainEvent event) {
		//ricevi l'evento e fai cose
		System.out.print("Ho ricevuto il messaggio ed eseguo il metodo onMessage\n");
		System.out.print(event.toString()+"\n");
	}
}
