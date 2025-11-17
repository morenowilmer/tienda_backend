package co.com.poli.tienda.controller;

import co.com.poli.tienda.adapter.port.LoginPort;
import co.com.poli.tienda.domain.common.GeneralResponse;
import co.com.poli.tienda.domain.enums.CodigoRespuestaEnum;
import co.com.poli.tienda.domain.enums.MensajesEnum;
import co.com.poli.tienda.domain.exception.NoAutorizadoException;
import co.com.poli.tienda.domain.requester.LoginRequester;
import co.com.poli.tienda.domain.response.LoginResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginPort loginPort;

    public LoginController(LoginPort loginPort) {
        this.loginPort = loginPort;
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<LoginResponse>> login(@RequestBody @Valid LoginRequester loginRequester) {
        try {
            LoginResponse response = loginPort.login(loginRequester);
            return ResponseEntity.ok(GeneralResponse.<LoginResponse>builder().respuesta(response).build());
        }catch (NoAutorizadoException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(GeneralResponse.<LoginResponse>builder().mensaje(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GeneralResponse.errorGenerico());
        }
    }

    @ResponseBody
    @PostMapping("/cerrar-sesion")
    public ResponseEntity<GeneralResponse<Boolean>> cerrarSesion(@RequestHeader("Authorization") @NotNull String authorization) {
        if (authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            Boolean response = loginPort.cerrarSesion(token);
            if (response) {
                return ResponseEntity.ok(GeneralResponse.<Boolean>builder()
                        .codigo(CodigoRespuestaEnum.EXITO.getValor())
                        .mensaje(MensajesEnum.CERRAR_SESION_EXITO.getValor())
                        .respuesta(response).build());
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
