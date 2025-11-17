package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.Factura;
import co.com.poli.tienda.domain.requester.FacturaRequester;

public interface FacturaPort {

    Factura guardarFactura(FacturaRequester factura);
}
