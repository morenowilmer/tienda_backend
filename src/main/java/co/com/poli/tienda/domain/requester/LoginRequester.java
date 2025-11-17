package co.com.poli.tienda.domain.requester;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequester implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String usuario;
    @NotBlank
    private String contrasena;
}
