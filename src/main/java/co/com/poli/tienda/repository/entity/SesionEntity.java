package co.com.poli.tienda.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SESION")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SesionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String usuario;
    private String token;
    @Column(name = "FECHA_INICIO")
    private LocalDateTime fechaInicio;
    @Column(name = "FECHA_FIN")
    private LocalDateTime fechaFin;
}
