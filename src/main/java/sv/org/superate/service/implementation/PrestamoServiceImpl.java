package sv.org.superate.service.implementation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.org.superate.controller.DTO.request.PrestamoRequest;
import sv.org.superate.controller.DTO.response.PrestamoResponse;
import sv.org.superate.repository.PrestamoRepository;
import sv.org.superate.repository.domain.Prestamo;
import sv.org.superate.service.EmailService;
import sv.org.superate.service.PrestamoService;
import sv.org.superate.service.mapper.PrestamoMapper;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final PrestamoMapper prestamoMapper;
    private final EmailService emailService;

    @Override
    public PrestamoResponse crearPrestamo(PrestamoRequest request) {
        Prestamo prestamo = prestamoMapper.toEntity(request);
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        try {
            emailService.enviarRecordatorioPrestamo(
                    prestamo.getCorreo(),
                    prestamo.getNombreLibro(),
                    prestamo.getNombreEstudiante(),
                    prestamo.getFechaFin()
            );
        } catch (MessagingException e) {
            System.err.println("Error en correo inicial: " + e.getMessage());
        }

        return prestamoMapper.toResponse(prestamoGuardado);
    }
}
