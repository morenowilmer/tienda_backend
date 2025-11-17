package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.DetalleFacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdDetalleFacturaRepository extends JpaRepository<DetalleFacturaEntity, Integer> {
}
