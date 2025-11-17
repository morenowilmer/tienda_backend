package co.com.poli.tienda.adapter.implement;

import co.com.poli.tienda.adapter.port.FacturaPort;
import co.com.poli.tienda.domain.common.DetalleFactura;
import co.com.poli.tienda.domain.common.Factura;
import co.com.poli.tienda.domain.common.Producto;
import co.com.poli.tienda.domain.requester.FacturaRequester;
import co.com.poli.tienda.repository.port.FacturaRepositoryPort;
import co.com.poli.tienda.repository.port.ProductoRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class FacturaAdapter implements FacturaPort {

    private final FacturaRepositoryPort facturaRepositoryPort;
    private final ProductoRepositoryPort productoRepositoryPort;
    private final ModelMapper modelMapper;

    public FacturaAdapter(FacturaRepositoryPort facturaRepositoryPort,
                          ProductoRepositoryPort productoRepositoryPort,
                          ModelMapper modelMapper) {
        this.facturaRepositoryPort = facturaRepositoryPort;
        this.productoRepositoryPort = productoRepositoryPort;
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
}
