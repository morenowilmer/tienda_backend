package co.com.poli.tienda.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodigoRespuestaEnum {
    ALERTA("ALERTA"),
    EXITO("OK"),
    ERROR("ERROR"),
    INFORMACION("INFO");

    private String valor;
}
