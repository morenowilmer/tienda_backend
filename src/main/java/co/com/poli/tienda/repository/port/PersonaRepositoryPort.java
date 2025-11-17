package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.Persona;
import co.com.poli.tienda.domain.common.TipoDocumento;

import java.util.List;

public interface PersonaRepositoryPort {

    Persona guardarPersona(Persona persona);
    Persona findById(Integer idPersona);
    Persona findByTipoDocumentoAndIdentificacion(Integer tipoDocumento, String identificacion);
    List<TipoDocumento> consultarTiposDocumentosActivos();
}
