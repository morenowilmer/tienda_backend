package co.com.poli.tienda.domain.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura implements Serializable {

    private Integer id;
    private String idFactura;
    private Integer idProducto;
    private Integer totalItems;
    private Integer precioProducto;
    private Integer impuesto;
    private Integer valorTotal;
}
