package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdPersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    PersonaEntity findByTipoDocumentoAndIdentificacion(Integer tipoDocumento, String identificacion);
}
