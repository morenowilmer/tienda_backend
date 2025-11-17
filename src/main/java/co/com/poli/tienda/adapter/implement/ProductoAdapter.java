package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.ProductoPort;
import co.com.poli.tienda.domain.common.Producto;
import co.com.poli.tienda.repository.port.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoAdapter implements ProductoPort {

    private final ProductoRepositoryPort productoRepositoryPort;

    public ProductoAdapter(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepositoryPort.guardarProducto(producto);
    }

    @Override
    public List<Producto> consultarProductos(String nombre) {
        return productoRepositoryPort.consultarProductoPorNombre(nombre);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepositoryPort.listarProductos();
    }
}
