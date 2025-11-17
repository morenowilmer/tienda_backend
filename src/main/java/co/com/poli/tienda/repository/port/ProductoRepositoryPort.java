package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.CategoriaProducto;
import co.com.poli.tienda.domain.common.Producto;

import java.util.List;

public interface ProductoRepositoryPort {

    Producto guardarProducto(Producto producto);
    List<CategoriaProducto> consultarCategorias();
}
