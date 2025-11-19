package co.com.poli.tienda.domain.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Factura")
public class FacturaReporte implements Serializable {

    private String id;
    private Integer idPersona;
    private Integer totalArticulos;
    private Integer totalVenta;
    private Integer totalImpuesto;
    private Integer totalFactura;
    private String fechaCompraFormateada;
    private String nombreCliente;
    private String identificacionCliente;
    @JacksonXmlElementWrapper(localName = "detallesFactura")
    @JacksonXmlProperty(localName = "detalle")
    private List<DetalleFactura> detallesFactura;
}
