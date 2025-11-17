package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdProductoRepository extends JpaRepository<ProductoEntity, Integer> {
}
