package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.DetalleFactura;
import co.com.poli.tienda.domain.common.Factura;

public interface FacturaRepositoryPort {

    Factura guardarFactura(Factura factura);
    DetalleFactura guardarDetalleFactura(DetalleFactura detalleFactura);
}
