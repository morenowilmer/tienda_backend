package co.com.poli.tienda.domain.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse implements Serializable {

    private String usuario;
    private Integer idPersona;
    private String activo;
}
