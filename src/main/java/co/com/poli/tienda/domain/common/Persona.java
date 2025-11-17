package co.com.poli.tienda.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements Serializable {

    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    private Integer tipoDocumento;
    @NotBlank
    private String identificacion;
    @NotBlank
    private String correo;
    @NotBlank
    private String celular;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate fechaNacimiento;
    private String departamento;
    private String ciudad;
    @NotBlank
    private String direccion;
}
