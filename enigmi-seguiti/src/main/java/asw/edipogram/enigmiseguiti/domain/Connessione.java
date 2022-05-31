package asw.edipogram.enigmiseguiti.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Connessione {

	@Id
	private Long id; 
	private String utente; 
	private String tipo; 
	
}
