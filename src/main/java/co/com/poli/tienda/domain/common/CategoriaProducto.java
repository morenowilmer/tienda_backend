package co.com.poli.tienda.domain.common;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProducto implements Serializable {

    private Integer id;
    @NotBlank
    private String nombre;
    private String descripcion;
}
