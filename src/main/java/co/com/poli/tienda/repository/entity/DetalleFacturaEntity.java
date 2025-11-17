package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
