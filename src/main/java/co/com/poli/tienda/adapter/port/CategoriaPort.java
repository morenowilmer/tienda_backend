package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.CategoriaProducto;

import java.util.List;

public interface CategoriaPort {

    CategoriaProducto guardarCategoria(CategoriaProducto categoria);
    CategoriaProducto consultarCategoria(String nombreCategoria);
    List<CategoriaProducto> consultarCategorias(String nombreCategoria);
    List<CategoriaProducto> consultarCategorias();
}
