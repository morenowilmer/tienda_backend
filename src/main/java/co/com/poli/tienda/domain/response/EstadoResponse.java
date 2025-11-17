package co.com.poli.tienda.domain.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoResponse implements Serializable {

    private String valor;
    private String nombre;
}
