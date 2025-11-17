package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    @Query("SELECT p FROM ProductoEntity p " +
            "WHERE UPPER(p.nombre) LIKE ?1")
    List<ProductoEntity> consultarProductosPorNombre(String nombre);
}
