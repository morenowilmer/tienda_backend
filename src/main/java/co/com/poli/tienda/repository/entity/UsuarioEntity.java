package co.com.poli.tienda.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements Serializable {

    @Id
    private String usuario;
    private String contrasena;
    @Column(name = "per_id")
    private Integer idPersona;
    private String activo;
}
