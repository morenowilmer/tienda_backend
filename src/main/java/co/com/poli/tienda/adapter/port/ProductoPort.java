package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.CategoriaProducto;
import co.com.poli.tienda.domain.common.Producto;

import java.util.List;

public interface ProductoPort {

    Producto guardarProducto(Producto producto);
    List<CategoriaProducto> consultarCategorias();
}
