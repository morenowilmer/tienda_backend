package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.Sesion;
import co.com.poli.tienda.repository.entity.SesionEntity;
import co.com.poli.tienda.repository.port.SesionRepositoryPort;
import co.com.poli.tienda.repository.repository.BdSesionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class SesionRepository implements SesionRepositoryPort {

    private final BdSesionRepository sesionRepository;
    private final ModelMapper modelMapper;

    public SesionRepository(BdSesionRepository sesionRepository,
                            ModelMapper modelMapper) {
        this.sesionRepository = sesionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Sesion consultarSesionPorToken(String token) {
        SesionEntity entity = sesionRepository.findByToken(token);
        return (Objects.nonNull(entity)) ? modelMapper.map(entity, Sesion.class) : null;
    }

    @Override
    public Sesion guardarSesion(Sesion sesion) {
        SesionEntity entity = modelMapper.map(sesion, SesionEntity.class);
        return modelMapper.map(sesionRepository.save(entity), Sesion.class);
    }

    @Override
    @Transactional
    public void eliminarSesion(String token) {
        sesionRepository.deleteByToken(token);
    }

    @Override
    public void eliminarSesionesUsuario(String usuario) {
        sesionRepository.deleteAllByUsuario(usuario);
    }
}
