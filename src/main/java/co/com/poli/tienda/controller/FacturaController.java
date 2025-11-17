package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.FacturaPort;
import co.com.poli.tienda.domain.common.Factura;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.requester.FacturaRequester;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/factura")
public class FacturaController {

    private final FacturaPort facturaPort;

    public FacturaController(FacturaPort facturaPort) {
        this.facturaPort = facturaPort;
    }

    @ResponseBody
    @PostMapping("/guardar")
    public ResponseEntity<GeneralResponse<Factura>> guardarFactura(
            @RequestBody @Valid FacturaRequester factura
    ) {
        try {
            Factura response = facturaPort.guardarFactura(factura);
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GeneralResponse.errorGenerico());
        }
    }
}
