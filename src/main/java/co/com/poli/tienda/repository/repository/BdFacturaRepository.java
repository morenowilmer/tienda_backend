package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdFacturaRepository extends JpaRepository<FacturaEntity, String> {

    List<FacturaEntity> findByIdPersona(Integer idPersona);
}
