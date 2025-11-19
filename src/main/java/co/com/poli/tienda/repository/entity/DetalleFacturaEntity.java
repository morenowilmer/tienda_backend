package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.io.Serializable;

@Entity
@Table(name = "DETALLE_FACTURA")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idFactura;
    private Integer idProducto;
    private Integer totalItems;
    private Integer precioProducto;
    private Integer impuesto;
    private Integer valorTotal;

    @Formula("(SELECT p.nombre FROM PRODUCTO p WHERE p.id = ID_PRODUCTO)")
    private String nombreProducto;
}
