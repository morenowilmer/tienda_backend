package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.Sesion;

public interface SesionRepositoryPort {

    Sesion consultarSesionPorToken(String token);
    Sesion guardarSesion(Sesion sesion);
    void eliminarSesion(String token);
    void eliminarSesionesUsuario(String usuario);
}
