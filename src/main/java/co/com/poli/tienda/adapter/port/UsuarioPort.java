package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.UsuarioDto;
import co.com.poli.tienda.domain.exception.TiendaException;
import co.com.poli.tienda.domain.response.EstadoResponse;
import co.com.poli.tienda.domain.response.UsuarioResponse;

import java.util.List;

public interface UsuarioPort {

    Boolean registarUsuario(UsuarioDto usuario) throws TiendaException;
    Boolean guardarUsuario(UsuarioDto usuario) throws TiendaException;
    UsuarioResponse consultarUsuario(String usuario) throws TiendaException;
    List<EstadoResponse> consultarEstados();
}
