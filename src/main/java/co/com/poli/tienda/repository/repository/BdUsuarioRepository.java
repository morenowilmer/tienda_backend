package co.com.poli.tienda.repository.repository;

import co.com.poli.tienda.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdUsuarioRepository extends JpaRepository<UsuarioEntity, String> {
}
