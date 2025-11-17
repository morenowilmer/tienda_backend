package co.com.poli.tienda.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadosEnum {
    ACTIVO("SI", "Activo"),
    NO_ACTIVO("NO", "Desactivo");

    private String valor;
    private String nombre;
}
