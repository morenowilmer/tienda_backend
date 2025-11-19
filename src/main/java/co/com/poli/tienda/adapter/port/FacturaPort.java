package co.com.poli.tienda.adapter.port;

import co.com.poli.tienda.domain.common.Factura;
import co.com.poli.tienda.domain.requester.FacturaRequester;

import java.util.List;

public interface FacturaPort {

    Factura guardarFactura(FacturaRequester factura);
    List<Factura> consultarFacturasCliente(Integer idPersona);
    String obtenerFacturaXml(String idFactura) throws Exception;
}
