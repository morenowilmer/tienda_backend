package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.PersonaPort;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.common.Persona;
import co.com.poli.tienda.domain.common.TipoDocumento;
import co.com.poli.tienda.domain.exception.TiendaException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static co.com.poli.tienda.domain.enums.MensajesEnum.*;

@Validated
@RestController
@RequestMapping("/persona")
public class PersonaController {

    private final PersonaPort personaPort;

    public PersonaController(PersonaPort personaPort) {
        this.personaPort = personaPort;
    }

    @ResponseBody
    @PostMapping("/guardar")
    public ResponseEntity<GeneralResponse<Persona>> guardarPersona(@RequestBody @Valid Persona persona) {
        try {
            Persona response = personaPort.guardarPersona(persona);
            if (Objects.nonNull(response) && Objects.nonNull(response.getId())) {
                return ResponseEntity.ok(GeneralResponse.exito(response));
            } else {
                return ResponseEntity.internalServerError()
                        .body(GeneralResponse.informacion(ERROR_GUARDANDO_ACTUALIZANDO.getValor()));
            }
        } catch (TiendaException te) {
            return ResponseEntity.internalServerError()
                    .body(GeneralResponse.error(te.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(GeneralResponse.errorGenerico());
        }
    }

    @ResponseBody
    @GetMapping("/consultar/{tipoDocumento}/{numeroIdentificacion}")
    public ResponseEntity<GeneralResponse<Persona>> consultarPersona(@PathVariable("tipoDocumento") @NotNull Integer tipoDocumento,
                                                                     @PathVariable("numeroIdentificacion") @NotNull String numeroIdentificacion) {
        Persona response = personaPort.consultarPersona(tipoDocumento, numeroIdentificacion);

        if (Objects.nonNull(response) && Objects.nonNull(response.getId())) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError()
                    .body(GeneralResponse.informacion(PERSONA_NO_ENCONTRADA.getValor()));
        }
    }

    @ResponseBody
    @GetMapping("/consultar/id/{idPersona}")
    public ResponseEntity<GeneralResponse<Persona>> consultarPersona(@PathVariable("idPersona") @NotNull Integer idPersona) {
        Persona response = personaPort.consultarPersonaPorId(idPersona);

        if (Objects.nonNull(response) && Objects.nonNull(response.getId())) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError()
                    .body(GeneralResponse.informacion(PERSONA_NO_ENCONTRADA.getValor()));
        }
    }

    @ResponseBody
    @GetMapping("/tipos-documentos")
    public ResponseEntity<GeneralResponse<List<TipoDocumento>>> consultarTiposDocumentos() {
        List<TipoDocumento> response = personaPort.consultarTiposDocumentos();
        if (!response.isEmpty()) {
            return ResponseEntity.ok(GeneralResponse.exito(response));
        } else {
            return ResponseEntity.internalServerError()
                    .body(GeneralResponse.informacion(SIN_DATOS.getValor()));
        }
    }
}
