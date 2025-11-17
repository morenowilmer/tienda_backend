package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.CategoriaPort;
import co.com.poli.tienda.domain.common.CategoriaProducto;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.enums.MensajesEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaPort categoriaPort;

    public CategoriaController(CategoriaPort categoriaPort) {
        this.categoriaPort = categoriaPort;
    }

    @ResponseBody
    @PostMapping("/guardar")
    public ResponseEntity<GeneralResponse<CategoriaProducto>> guardarCategoria(
            @RequestBody @Valid CategoriaProducto categoria
    ) {
        CategoriaProducto response = categoriaPort.guardarCategoria(categoria);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError().body(GeneralResponse.errorGenerico());
        }
    }

    @ResponseBody
    @GetMapping("/consultar/{nombreCategoria}")
    public ResponseEntity<GeneralResponse<CategoriaProducto>> consultarCategoria(
            @PathVariable("nombreCategoria") @NotNull String nombreCategoria
    ) {
        CategoriaProducto response = categoriaPort.consultarCategoria(nombreCategoria);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError().body(GeneralResponse
                    .error(MensajesEnum.SIN_DATOS.getValor()));
        }
    }

    @ResponseBody
    @GetMapping("/consultar-coincidencias/{nombreCategoria}")
    public ResponseEntity<GeneralResponse<List<CategoriaProducto>>> consultarCategorias(
            @PathVariable("nombreCategoria") @NotNull String nombreCategoria
    ) {
        List<CategoriaProducto> response = categoriaPort.consultarCategorias(nombreCategoria);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError().body(GeneralResponse
                    .error(MensajesEnum.SIN_DATOS.getValor()));
        }
    }

    @ResponseBody
    @GetMapping("/listar")
    public ResponseEntity<GeneralResponse<List<CategoriaProducto>>> consultarTodasCategorias() {
        List<CategoriaProducto> response = categoriaPort.consultarCategorias();
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError().body(GeneralResponse
                    .error(MensajesEnum.SIN_DATOS.getValor()));
        }
    }
}
