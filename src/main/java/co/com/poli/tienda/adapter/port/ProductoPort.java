package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.Producto;

import java.util.List;

public interface ProductoPort {

    Producto guardarProducto(Producto producto);
    List<Producto> consultarProductos(String nombre);
    List<Producto> listarProductos();
}
