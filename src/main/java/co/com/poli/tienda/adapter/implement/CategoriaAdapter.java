package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.CategoriaPort;
import co.com.poli.tienda.domain.common.CategoriaProducto;
import co.com.poli.tienda.repository.port.CategoriaRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaAdapter implements CategoriaPort {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public CategoriaAdapter(CategoriaRepositoryPort categoriaRepositoryPort) {
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public CategoriaProducto guardarCategoria(CategoriaProducto categoria) {
        return categoriaRepositoryPort.guardar(categoria);
    }

    @Override
    public CategoriaProducto consultarCategoria(String nombreCategoria) {
        return categoriaRepositoryPort.consultarPorNombre(nombreCategoria);
    }

    @Override
    public List<CategoriaProducto> consultarCategorias(String nombreCategoria) {
        return categoriaRepositoryPort.consultarCategoriasPorNombre(nombreCategoria);
    }

    @Override
    public List<CategoriaProducto> consultarCategorias() {
        return categoriaRepositoryPort.consultarTodas();
    }
}
