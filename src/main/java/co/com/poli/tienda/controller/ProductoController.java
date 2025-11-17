package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.ProductoPort;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.common.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/producto")
@Validated
public class ProductoController {

    private final ProductoPort productoPort;

    public ProductoController(ProductoPort productoPort) {
        this.productoPort = productoPort;
    }

    @ResponseBody
    @PostMapping("/guardar")
    public ResponseEntity<GeneralResponse<Producto>> guardarProducto(@RequestBody Producto producto) {
        Producto response = productoPort.guardarProducto(producto);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError().body(GeneralResponse.errorGenerico());
        }
    }
}
