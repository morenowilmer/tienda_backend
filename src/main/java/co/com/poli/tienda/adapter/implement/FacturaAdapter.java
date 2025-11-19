package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.FacturaPort;
import co.com.poli.tienda.domain.common.*;
import co.com.poli.tienda.domain.exception.TiendaException;
import co.com.poli.tienda.domain.requester.FacturaRequester;
import co.com.poli.tienda.repository.port.FacturaRepositoryPort;
import co.com.poli.tienda.repository.port.PersonaRepositoryPort;
import co.com.poli.tienda.repository.port.ProductoRepositoryPort;
import co.com.poli.tienda.util.TransformarXml;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FacturaAdapter implements FacturaPort {

    private final FacturaRepositoryPort facturaRepositoryPort;
    private final ProductoRepositoryPort productoRepositoryPort;
    private final PersonaRepositoryPort personaRepositoryPort;
    private final TransformarXml transformarXml;
    private final ModelMapper modelMapper;

    private final XmlMapper xmlMapper = new XmlMapper();

    public FacturaAdapter(FacturaRepositoryPort facturaRepositoryPort,
                          ProductoRepositoryPort productoRepositoryPort,
                          PersonaRepositoryPort personaRepositoryPort,
                          TransformarXml transformarXml,
                          ModelMapper modelMapper) {
        this.facturaRepositoryPort = facturaRepositoryPort;
        this.productoRepositoryPort = productoRepositoryPort;
        this.personaRepositoryPort = personaRepositoryPort;
        this.transformarXml = transformarXml;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Factura guardarFactura(FacturaRequester requester) {
        Factura factura = modelMapper.map(requester, Factura.class);
        factura.setId(UUID.randomUUID().toString());
        factura.setTotalArticulos(requester.getDetallesFactura().size());
        factura.setFechaCompra(LocalDate.now());

        factura = facturaRepositoryPort.guardarFactura(factura);

        for (DetalleFactura detalle : requester.getDetallesFactura()) {
            detalle.setIdFactura(factura.getId());
            facturaRepositoryPort.guardarDetalleFactura(detalle);

            Producto productoDetalle = productoRepositoryPort
                    .buscarPorId(detalle.getIdProducto());
            productoDetalle.setCantidad(productoDetalle.getCantidad()-detalle.getTotalItems());
            productoRepositoryPort.guardarProducto(productoDetalle);
        }
        return factura;
    }

    @Override
    public List<Factura> consultarFacturasCliente(Integer idPersona) {
        return facturaRepositoryPort.consultarFacturasCliente(idPersona);
    }

    @Override
    public String obtenerFacturaXml(String idFactura) throws Exception {
        Factura factura = facturaRepositoryPort.consultarFacturaPorId(idFactura);

        if (Objects.isNull(factura))
            throw new TiendaException("Factura no encontrada");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        FacturaReporte facturaReporte = modelMapper.map(factura, FacturaReporte.class);
        facturaReporte.setFechaCompraFormateada(factura.getFechaCompra().format(formatter));

        List<DetalleFactura> detalles = facturaRepositoryPort
                .consultarDetallesFactura(factura.getId());

        Persona cliente = personaRepositoryPort.findById(factura.getIdPersona());
        facturaReporte.setNombreCliente(cliente.getNombre().concat(" ").concat(cliente.getApellido()));
        facturaReporte.setIdentificacionCliente(cliente.getIdentificacion());

        facturaReporte.setDetallesFactura(detalles);

        String xmlData = xmlMapper.writeValueAsString(facturaReporte);
        return transformarXml.convertir(xmlData, "reportes/factura.xsl");
    }
}
