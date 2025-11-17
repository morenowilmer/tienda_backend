package co.com.poli.tienda.domain.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento implements Serializable {

    private Integer id;
    private String nombre;
    private String valor;
    private String activo;
}
