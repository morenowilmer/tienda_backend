package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.UsuarioDto;
import co.com.poli.tienda.repository.entity.UsuarioEntity;
import co.com.poli.tienda.repository.port.UsuarioRepositoryPort;
import co.com.poli.tienda.repository.repository.BdUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioRepository implements UsuarioRepositoryPort {

    private final BdUsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioRepository(BdUsuarioRepository usuarioRepository,
                             ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioDto consultarPorUsuario(String usuario) {
        Optional<UsuarioEntity> entity = usuarioRepository.findById(usuario);
        return entity.map(usuarioEntity -> modelMapper.map(usuarioEntity, UsuarioDto.class)).orElse(null);
    }

    @Override
    public UsuarioDto guardarUsuario(UsuarioDto usuario) {
        UsuarioEntity entity = modelMapper.map(usuario, UsuarioEntity.class);
        return modelMapper.map(usuarioRepository.save(entity), UsuarioDto.class);
    }
}
