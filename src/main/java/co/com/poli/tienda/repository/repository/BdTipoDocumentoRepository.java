package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdTipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Integer> {

    List<TipoDocumentoEntity> findAllByActivo(String activo);
}
