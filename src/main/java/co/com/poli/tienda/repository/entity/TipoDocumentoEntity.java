package co.com.poli.tienda.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "TIPO_DOCUMENTO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoEntity implements Serializable {

    @Id
    private Integer id;
    private String nombre;
    private String valor;
    private String activo;
}
