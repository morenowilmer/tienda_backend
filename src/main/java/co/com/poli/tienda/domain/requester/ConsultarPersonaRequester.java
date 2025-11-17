package co.com.poli.tienda.domain.requester;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarPersonaRequester implements Serializable {

    @NotBlank
    private String identificacion;
    @NotNull
    private Integer tipoDocumento;
}
