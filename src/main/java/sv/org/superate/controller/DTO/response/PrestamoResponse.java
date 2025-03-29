package sv.org.superate.controller.DTO.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoResponse {
    private Long id;
    private String autor;
    private String nombreLibro;
    private String nombreEstudiante;
    private String carnet;
    private String correo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}