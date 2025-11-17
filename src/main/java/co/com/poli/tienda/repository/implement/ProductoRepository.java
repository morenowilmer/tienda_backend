package co.com.poli.tienda.repository.implement;

import co.com.poli.tienda.domain.common.Producto;
import co.com.poli.tienda.repository.entity.ProductoEntity;
import co.com.poli.tienda.repository.port.ProductoRepositoryPort;
import co.com.poli.tienda.repository.repository.BdProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoRepository implements ProductoRepositoryPort {

    private final BdProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    public ProductoRepository(BdProductoRepository productoRepository,
                              ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        ProductoEntity entity = modelMapper.map(producto, ProductoEntity.class);
        return modelMapper.map(productoRepository.save(entity), Producto.class);
    }

    @Override
    public List<Producto> consultarProductoPorNombre(String nombreProducto) {
        List<ProductoEntity> productos = productoRepository
                .consultarProductosPorNombre("%"+nombreProducto.toUpperCase()+"%");
        return productos.stream().map(p -> modelMapper.map(p, Producto.class)).toList();
    }

    @Override
    public List<Producto> listarProductos() {
        List<ProductoEntity> productos = productoRepository.findAll();
        return productos.stream().map(p -> modelMapper.map(p, Producto.class)).toList();
    }

    @Override
    public Producto buscarPorId(Integer idProducto) {
        Optional<ProductoEntity> entity = productoRepository.findById(idProducto);
        return (entity.isPresent()) ? modelMapper.map(entity, Producto.class) : null;
    }
}
