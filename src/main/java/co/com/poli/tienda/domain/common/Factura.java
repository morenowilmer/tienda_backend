package co.com.poli.tienda.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Factura implements Serializable {

    private String id;
    private Integer idPersona;
    private Integer totalArticulos;
    private Integer totalVenta;
    private Integer totalImpuesto;
    private Integer totalFactura;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate fechaCompra;
}
