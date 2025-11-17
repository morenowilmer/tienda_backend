package co.com.poli.tienda.domain.common;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sesion implements Serializable {

    private Long id;
    private String usuario;
    private String token;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
