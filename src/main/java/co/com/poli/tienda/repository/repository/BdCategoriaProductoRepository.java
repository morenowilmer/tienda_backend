package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.CategoriaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdCategoriaProductoRepository extends JpaRepository<CategoriaProductoEntity, Integer> {
}
