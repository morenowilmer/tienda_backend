package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "CATEGORIA_PRODUCTO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProductoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
}
