package co.com.poli.tienda.repository.port;

import co.com.poli.tienda.domain.common.DetalleFactura;
import co.com.poli.tienda.domain.common.Factura;

import java.util.List;

public interface FacturaRepositoryPort {

    Factura consultarFacturaPorId(String idFactura);
    Factura guardarFactura(Factura factura);
    DetalleFactura guardarDetalleFactura(DetalleFactura detalleFactura);
    List<Factura> consultarFacturasCliente(Integer idPersona);
    List<DetalleFactura> consultarDetallesFactura(String idFactura);
}
