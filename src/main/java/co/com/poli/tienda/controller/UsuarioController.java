package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.UsuarioPort;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.common.UsuarioDto;
import co.com.poli.tienda.domain.exception.TiendaException;
import co.com.poli.tienda.domain.response.EstadoResponse;
import co.com.poli.tienda.domain.response.UsuarioResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioPort usuarioPort;

    public UsuarioController(UsuarioPort usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    @ResponseBody
    @PostMapping("/registrar-usuario")
    public ResponseEntity<GeneralResponse<Boolean>> registrarUsuario(@RequestBody @Valid UsuarioDto usuario) {
        try {
            Boolean response = usuarioPort.registarUsuario(usuario);
            if (response) {
                return ResponseEntity.ok(GeneralResponse.<Boolean>builder().respuesta(response).build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (TiendaException te) {
            return ResponseEntity.internalServerError().body(GeneralResponse.error(te.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GeneralResponse.errorGenerico());
        }
    }

    @ResponseBody
    @PostMapping("/guardar-usuario")
    public ResponseEntity<GeneralResponse<Boolean>> guardarUsuario(@RequestBody @Valid UsuarioDto usuario) {
        try {
            Boolean response = usuarioPort.guardarUsuario(usuario);
            if (response) {
                return ResponseEntity.ok(GeneralResponse.<Boolean>builder().respuesta(response).build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (TiendaException te) {
            return ResponseEntity.internalServerError().body(GeneralResponse.error(te.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(GeneralResponse.errorGenerico());
        }
    }

    @ResponseBody
    @GetMapping("/consultar/{usuario}")
    public ResponseEntity<GeneralResponse<UsuarioResponse>> consultarUsuario(@PathVariable("usuario") @NotNull String usuario) {
        try {
            UsuarioResponse response = usuarioPort.consultarUsuario(usuario);

            return ResponseEntity.ok(GeneralResponse.exito(response));
        } catch (TiendaException te) {
            return ResponseEntity.internalServerError().body(GeneralResponse.error(te.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/consultar-estados")
    public ResponseEntity<GeneralResponse<List<EstadoResponse>>> consultarEstados() {
       List<EstadoResponse> response = usuarioPort.consultarEstados();

       if (!response.isEmpty()) {
           return ResponseEntity.ok(GeneralResponse.exito(response));
       } else {
           return ResponseEntity.noContent().build();
       }
    }
}
