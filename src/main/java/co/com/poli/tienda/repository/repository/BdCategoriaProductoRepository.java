package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.CategoriaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdCategoriaProductoRepository extends JpaRepository<CategoriaProductoEntity, Integer> {

    CategoriaProductoEntity findByNombre(String nombre);

    @Query("SELECT c FROM CategoriaProductoEntity c " +
            "WHERE UPPER(c.nombre) LIKE ?1")
    List<CategoriaProductoEntity> consultarPorNombre(String nombreCategoria);
}
