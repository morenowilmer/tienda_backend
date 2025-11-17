package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PERSONA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    @Column(name = "TIPO_DOC")
    private Integer tipoDocumento;
    private String identificacion;
    private String correo;
    private String celular;
    private LocalDate fechaNacimiento;
    private String departamento;
    private String ciudad;
    private String direccion;
}
