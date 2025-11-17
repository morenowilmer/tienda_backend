package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.SesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdSesionRepository extends JpaRepository<SesionEntity, Integer> {

    SesionEntity findByToken(String token);
    void deleteByToken(String token);
    void deleteAllByUsuario(String usuario);
}
