package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.LoginPort;
import co.com.poli.tienda.domain.common.Persona;
import co.com.poli.tienda.domain.common.Sesion;
import co.com.poli.tienda.domain.common.UsuarioDto;
import co.com.poli.tienda.domain.enums.MensajesEnum;
import co.com.poli.tienda.domain.exception.NoAutorizadoException;
import co.com.poli.tienda.domain.requester.LoginRequester;
import co.com.poli.tienda.domain.response.LoginResponse;
import co.com.poli.tienda.repository.port.PersonaRepositoryPort;
import co.com.poli.tienda.repository.port.SesionRepositoryPort;
import co.com.poli.tienda.repository.port.UsuarioRepositoryPort;
import co.com.poli.tienda.util.CifradoUtil;
import co.com.poli.tienda.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class LoginAdapter implements LoginPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final SesionRepositoryPort sesionRepositoryPort;
    private final PersonaRepositoryPort personaRepositoryPort;
    private final JwtUtil jwtUtil;

    private static final Double MILI_MINUTOS = 0.0000166667;

    @Value("${timepo.token.milisegundos}")
    private Long tiempoMilisegundos;

    public LoginAdapter(UsuarioRepositoryPort usuarioRepositoryPort,
                        SesionRepositoryPort sesionRepositoryPort,
                        PersonaRepositoryPort personaRepositoryPort,
                        JwtUtil jwtUtil) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.sesionRepositoryPort = sesionRepositoryPort;
        this.personaRepositoryPort = personaRepositoryPort;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(LoginRequester loginRequester) throws NoAutorizadoException {
        UsuarioDto usuario = usuarioRepositoryPort
                .consultarPorUsuario(loginRequester.getUsuario());

        if (Objects.isNull(usuario))
            throw new NoAutorizadoException(MensajesEnum.USUARIO_NO_ENCONTRADO.getValor());

        String contrasenaCifrada = CifradoUtil.cifrar(loginRequester.getContrasena());

        if (!Objects.equals(contrasenaCifrada, usuario.getContrasena()))
            throw new NoAutorizadoException(MensajesEnum.CONTRASENA_ERRONEA.getValor());

        Double tiempoAdicionar = tiempoMilisegundos*MILI_MINUTOS;
        String token = jwtUtil.generarToken(usuario.getUsuario());
        Sesion sesion = Sesion.builder()
                .token(token)
                .usuario(usuario.getUsuario())
                .fechaInicio(LocalDateTime.now())
                .fechaFin(LocalDateTime.now().plusMinutes(tiempoAdicionar.longValue()))
                .build();
        sesionRepositoryPort.guardarSesion(sesion);

        Persona persona = personaRepositoryPort.findById(usuario.getIdPersona());
        return LoginResponse.builder()
                .nombreUsuario(persona.getNombre())
                .token(token).build();
    }

    @Override
    public Boolean registrarUSuario(UsuarioDto usuario) {
        String contrasena = CifradoUtil.cifrar(usuario.getContrasena());
        usuario.setContrasena(contrasena);
        usuario.setActivo("SI");
        try {
            usuarioRepositoryPort.guardarUsuario(usuario);
            return true;
        }  catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean cerrarSesion(String token) {
        sesionRepositoryPort.eliminarSesion(token);
        return true;
    }
}
