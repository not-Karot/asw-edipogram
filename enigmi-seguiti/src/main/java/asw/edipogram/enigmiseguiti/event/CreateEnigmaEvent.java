package asw.edipogram.enigmiseguiti.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CreateEnigmaEvent implements DomainEvent{

    private Long id;
    private String autore;
    private String tipo;
    private String tipoSpecifico;
    private String titolo;
    private String[] testo;
    private String[] soluzione;

}
