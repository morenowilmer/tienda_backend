package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.FacturaPort;
import co.com.poli.tienda.domain.common.Factura;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.exception.TiendaException;
import co.com.poli.tienda.domain.requester.FacturaRequester;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseBody
    @GetMapping("/consultar/cliente/{idPersona}")
    public ResponseEntity<GeneralResponse<List<Factura>>> consultarFacturasCliente(
            @PathVariable("idPersona") @NotNull Integer idPersona
    ) {
        List<Factura> response = facturaPort.consultarFacturasCliente(idPersona);
        if (!response.isEmpty()) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/xml/{idFactura}")
    public ResponseEntity<GeneralResponse<String>> consultarFacturaXml(
            @PathVariable("idFactura") @NotBlank String idFactura
    ) {
        try {
            String response = facturaPort.obtenerFacturaXml(idFactura);
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } catch (TiendaException te) {
            return ResponseEntity.internalServerError().body(GeneralResponse.error(te.getMessage()));
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.internalServerError().body(GeneralResponse.errorGenerico());
        }
    }
}
