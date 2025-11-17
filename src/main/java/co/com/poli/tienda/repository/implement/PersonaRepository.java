package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.Persona;
import co.com.poli.tienda.domain.common.TipoDocumento;
import co.com.poli.tienda.repository.entity.PersonaEntity;
import co.com.poli.tienda.repository.entity.TipoDocumentoEntity;
import co.com.poli.tienda.repository.port.PersonaRepositoryPort;
import co.com.poli.tienda.repository.repository.BdPersonaRepository;
import co.com.poli.tienda.repository.repository.BdTipoDocumentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonaRepository implements PersonaRepositoryPort {

    private final BdPersonaRepository personaRepository;
    private final BdTipoDocumentoRepository tipoDocumentoRepository;
    private final ModelMapper modelMapper;

    public PersonaRepository(BdPersonaRepository personaRepository,
                             BdTipoDocumentoRepository tipoDocumentoRepository,
                             ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        PersonaEntity entity = modelMapper.map(persona, PersonaEntity.class);
        return modelMapper.map(personaRepository.save(entity), Persona.class);
    }

    @Override
    public Persona findById(Integer idPersona) {
        Optional<PersonaEntity> entity = personaRepository.findById(idPersona);
        return entity.map(e -> modelMapper.map(e, Persona.class)).orElse(null);
    }

    @Override
    public Persona findByTipoDocumentoAndIdentificacion(Integer tipoDocumento, String identificacion) {
        PersonaEntity entity =  personaRepository.findByTipoDocumentoAndIdentificacion(tipoDocumento, identificacion);
        return (Objects.nonNull(entity)) ? modelMapper.map(entity, Persona.class) : null;
    }

    @Override
    public List<TipoDocumento> consultarTiposDocumentosActivos() {
        List<TipoDocumentoEntity> entities = tipoDocumentoRepository.findAllByActivo("S");
        return entities.stream().map(entity -> modelMapper.map(entity, TipoDocumento.class)).toList();
    }
}
