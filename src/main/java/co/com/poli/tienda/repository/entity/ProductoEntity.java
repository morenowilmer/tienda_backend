package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "PRODUCTO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private String codigoBarras;
    private Integer precioCompra;
    private Integer precioVenta;
    private Integer idCategoria;
}
