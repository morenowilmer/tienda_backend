package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.CategoriaProducto;
import co.com.poli.tienda.domain.common.Producto;
import co.com.poli.tienda.repository.entity.CategoriaProductoEntity;
import co.com.poli.tienda.repository.entity.ProductoEntity;
import co.com.poli.tienda.repository.port.ProductoRepositoryPort;
import co.com.poli.tienda.repository.repository.BdCategoriaProductoRepository;
import co.com.poli.tienda.repository.repository.BdProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductoRepository implements ProductoRepositoryPort {

    private final BdProductoRepository productoRepository;
    private final BdCategoriaProductoRepository categoriaProductoRepository;
    private final ModelMapper modelMapper;

    public ProductoRepository(BdProductoRepository productoRepository,
                              BdCategoriaProductoRepository categoriaProductoRepository,
                              ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.categoriaProductoRepository = categoriaProductoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        ProductoEntity entity = modelMapper.map(producto, ProductoEntity.class);
        return modelMapper.map(productoRepository.save(entity), Producto.class);
    }

    @Override
    public List<CategoriaProducto> consultarCategorias() {
        List<CategoriaProductoEntity> categorias = categoriaProductoRepository.findAll();
        return (categorias.isEmpty()) ? Collections.emptyList() :
                categorias.stream().map(entity -> modelMapper.map(entity, CategoriaProducto.class)).toList();
    }
}
