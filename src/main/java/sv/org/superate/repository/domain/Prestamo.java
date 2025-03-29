package sv.org.superate.repository.domain;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String autor;

    @Column(name = "nombre_libro")
    @NotNull
    private String nombreLibro;

    @Column(name = "nombre_estudiante")
    @NotNull
    private String nombreEstudiante;

    @NotNull
    private String carnet;

    @NotNull
    private String correo;

    @Column(name = "fecha_inicio")
    @NotNull
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @NotNull
    private LocalDate fechaFin;
}