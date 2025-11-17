package co.com.poli.tienda.domain.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {

    private String nombreUsuario;
    private String token;
}
