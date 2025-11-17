package co.com.poli.tienda.domain.requester;

import co.com.poli.tienda.domain.common.DetalleFactura;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRequester implements Serializable {

    private Integer idPersona;
    private Integer totalArticulos;
    private Integer totalVenta;
    private Integer totalImpuesto;
    private Integer totalFactura;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate fechaCompra;
    private List<DetalleFactura> detallesFactura;
}
