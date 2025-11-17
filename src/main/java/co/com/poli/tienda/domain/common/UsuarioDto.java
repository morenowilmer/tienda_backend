package co.com.poli.tienda.domain.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto implements Serializable {

    @NotBlank
    private String usuario;
    @NotBlank
    private String contrasena;
    @NotNull
    private Integer idPersona;
    private String activo;
}
