package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.UsuarioDto;

public interface UsuarioRepositoryPort {

    UsuarioDto consultarPorUsuario(String usuario);
    UsuarioDto guardarUsuario(UsuarioDto usuario);
}
