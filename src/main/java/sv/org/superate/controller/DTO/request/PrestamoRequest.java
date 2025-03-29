package sv.org.superate.controller.DTO.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRequest {
    @NotBlank
    private String autor;

    @NotBlank
    private String nombreLibro;

    @NotBlank
    private String nombreEstudiante;

    @NotBlank
    private String carnet;

    @NotBlank
    @Email
    private String correo;

    @NotNull
    @FutureOrPresent
    private LocalDate fechaInicio;

    @NotNull
    @Future
    private LocalDate fechaFin;
}