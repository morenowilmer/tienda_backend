package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "FACTURA")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaEntity implements Serializable {

    @Id
    private String id;
    private Integer idPersona;
    private Integer totalArticulos;
    private Integer totalVenta;
    private Integer totalImpuesto;
    private Integer totalFactura;
    private LocalDate fechaCompra;
}
