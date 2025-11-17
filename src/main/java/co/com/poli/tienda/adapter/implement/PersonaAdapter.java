package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.PersonaPort;
import co.com.poli.tienda.domain.common.Persona;
import co.com.poli.tienda.domain.common.TipoDocumento;
import co.com.poli.tienda.domain.exception.TiendaException;
import co.com.poli.tienda.repository.port.PersonaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static co.com.poli.tienda.domain.enums.MensajesEnum.PERSONA_REGISTRADA_ERROR;

@Service
public class PersonaAdapter implements PersonaPort {

    private final PersonaRepositoryPort personaRepository;

    public PersonaAdapter(PersonaRepositoryPort personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona guardarPersona(Persona persona) throws TiendaException {
        if (Objects.isNull(persona.getId())) {
            Persona personaExistente = personaRepository
                    .findByTipoDocumentoAndIdentificacion(persona.getTipoDocumento(), persona.getIdentificacion());

            if (Objects.nonNull(personaExistente))
                throw new TiendaException(PERSONA_REGISTRADA_ERROR.getValor());
        }

        return personaRepository.guardarPersona(persona);
    }

    @Override
    public Persona consultarPersona(Integer tipoDocumento, String identificacion) {
        return personaRepository.findByTipoDocumentoAndIdentificacion(tipoDocumento, identificacion);
    }

    @Override
    public Persona consultarPersonaPorId(Integer idPersona) {
        return personaRepository.findById(idPersona);
    }

    @Override
    public List<TipoDocumento> consultarTiposDocumentos() {
        return personaRepository.consultarTiposDocumentosActivos();
    }
}
