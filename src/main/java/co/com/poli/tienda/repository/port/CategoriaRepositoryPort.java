package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.CategoriaProducto;

import java.util.List;

public interface CategoriaRepositoryPort {

    CategoriaProducto guardar(CategoriaProducto categoria);
    CategoriaProducto consultarPorNombre(String nombreCategoria);
    List<CategoriaProducto> consultarCategoriasPorNombre(String nombreCategoria);
    List<CategoriaProducto> consultarTodas();
}
