package co.com.poli.tienda.domain.common;

import co.com.poli.tienda.domain.enums.CodigoRespuestaEnum;
import co.com.poli.tienda.domain.enums.MensajesEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse <T> {

    public String codigo;
    public String mensaje;
    public T respuesta;

    public static <T> GeneralResponse<T> informacion(String mensaje) {
        return new GeneralResponse<>(CodigoRespuestaEnum.INFORMACION.getValor(), mensaje, null);
    }

    public static <T> GeneralResponse<T> error(String mensaje) {
        return new GeneralResponse<>(CodigoRespuestaEnum.INFORMACION.getValor(), mensaje, null);
    }

    public static <T> GeneralResponse<T> errorGenerico() {
        return new GeneralResponse<>(CodigoRespuestaEnum.ERROR.getValor(), MensajesEnum.ERROR_GENERAL.getValor(), null);
    }

    public static <T> GeneralResponse<T> exito(T respuesta) {
        return new GeneralResponse<>(CodigoRespuestaEnum.EXITO.getValor(), null, respuesta);
    }
}
