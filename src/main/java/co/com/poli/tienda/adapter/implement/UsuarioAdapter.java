package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.UsuarioPort;
import co.com.poli.tienda.domain.common.UsuarioDto;
import co.com.poli.tienda.domain.enums.EstadosEnum;
import co.com.poli.tienda.domain.exception.TiendaException;
import co.com.poli.tienda.domain.response.EstadoResponse;
import co.com.poli.tienda.domain.response.UsuarioResponse;
import co.com.poli.tienda.repository.port.UsuarioRepositoryPort;
import co.com.poli.tienda.util.CifradoUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static co.com.poli.tienda.domain.enums.MensajesEnum.USUARIO_NO_ENCONTRADO;
import static co.com.poli.tienda.domain.enums.MensajesEnum.USUARIO_REGISTRADO;

@Service
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    public UsuarioAdapter(UsuarioRepositoryPort usuarioRepositoryPort,
                          ModelMapper modelMapper) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean registarUsuario(UsuarioDto usuario) throws TiendaException {
        UsuarioDto usuarioEncontrado = usuarioRepositoryPort.consultarPorUsuario(usuario.getUsuario());

        if (Objects.nonNull(usuarioEncontrado))
            throw new TiendaException(USUARIO_REGISTRADO.getValor());

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
    public Boolean guardarUsuario(UsuarioDto usuario) throws TiendaException {
        UsuarioDto usuarioEncontrado = usuarioRepositoryPort.consultarPorUsuario(usuario.getUsuario());

        if (Objects.isNull(usuarioEncontrado))
            throw new TiendaException(USUARIO_NO_ENCONTRADO.getValor());

        String contrasena = CifradoUtil.cifrar(usuario.getContrasena());
        usuario.setContrasena(contrasena);
        try {
            usuarioRepositoryPort.guardarUsuario(usuario);
            return true;
        }  catch (Exception e) {
            return false;
        }
    }

    @Override
    public UsuarioResponse consultarUsuario(String usuario) throws TiendaException {
        UsuarioDto usuarioEncontrado = usuarioRepositoryPort.consultarPorUsuario(usuario);

        if (Objects.isNull(usuarioEncontrado))
            throw new TiendaException(USUARIO_NO_ENCONTRADO.getValor());

        return modelMapper.map(usuarioEncontrado, UsuarioResponse.class);
    }

    @Override
    public List<EstadoResponse> consultarEstados() {
        return Arrays.stream(EstadosEnum.values())
                .map(e -> EstadoResponse.builder()
                        .valor(e.getValor())
                        .nombre(e.getNombre()).build()).toList();
    }
}
