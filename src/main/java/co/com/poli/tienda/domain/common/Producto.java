package co.com.poli.tienda.domain.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {

    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotNull
    private Integer cantidad;
    private String codigoBarras;
    @NotNull
    private Integer precioCompra;
    @NotNull
    private Integer precioVenta;
    @NotNull
    private Integer idCategoria;
}
