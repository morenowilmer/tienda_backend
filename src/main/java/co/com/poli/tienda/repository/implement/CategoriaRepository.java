package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.CategoriaProducto;
import co.com.poli.tienda.repository.entity.CategoriaProductoEntity;
import co.com.poli.tienda.repository.port.CategoriaRepositoryPort;
import co.com.poli.tienda.repository.repository.BdCategoriaProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoriaRepository implements CategoriaRepositoryPort {

    private final BdCategoriaProductoRepository categoriaProductoRepository;
    private final ModelMapper modelMapper;

    public CategoriaRepository(BdCategoriaProductoRepository categoriaProductoRepository,
                               ModelMapper modelMapper) {
        this.categoriaProductoRepository = categoriaProductoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoriaProducto guardar(CategoriaProducto categoria) {
        CategoriaProductoEntity entity = modelMapper.map(categoria, CategoriaProductoEntity.class);
        return modelMapper.map(categoriaProductoRepository.save(entity), CategoriaProducto.class);
    }

    @Override
    public CategoriaProducto consultarPorNombre(String nombreCategoria) {
        CategoriaProductoEntity entity = categoriaProductoRepository.findByNombre(nombreCategoria);
        return (Objects.nonNull(entity)) ? modelMapper.map(entity, CategoriaProducto.class) : null;
    }

    @Override
    public List<CategoriaProducto> consultarCategoriasPorNombre(String nombreCategoria) {
        List<CategoriaProductoEntity> entities = categoriaProductoRepository
                .consultarPorNombre("%" + nombreCategoria.toUpperCase() + "%");
        return entities.stream().map(c -> modelMapper.map(c, CategoriaProducto.class)).toList();
    }

    @Override
    public List<CategoriaProducto> consultarTodas() {
        List<CategoriaProductoEntity> categorias = categoriaProductoRepository.findAll();
        return categorias.stream().map(c -> modelMapper.map(c, CategoriaProducto.class)).toList();
    }
}
