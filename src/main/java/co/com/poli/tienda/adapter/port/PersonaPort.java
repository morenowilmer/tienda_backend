package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.Persona;
import co.com.poli.tienda.domain.common.TipoDocumento;
import co.com.poli.tienda.domain.exception.TiendaException;

import java.util.List;

public interface PersonaPort {

    Persona guardarPersona(Persona persona) throws TiendaException;
    Persona consultarPersona(Integer tipoDocumento, String identificacion);
    Persona consultarPersonaPorId(Integer idPersona);
    List<TipoDocumento> consultarTiposDocumentos();
}
