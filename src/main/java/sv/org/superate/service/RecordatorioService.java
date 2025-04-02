package sv.org.superate.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sv.org.superate.repository.PrestamoRepository;
import sv.org.superate.repository.domain.Prestamo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordatorioService {
    private final PrestamoRepository prestamoRepository;
    private final EmailService emailService;

    // Se ejecuta diario a las 9:00 AM (GMT-6)
    // @Scheduled(fixedRate = 300000) -> 300,000 ms = 5 minutos
    @Scheduled(cron = "0 0 9 * * ?", zone = "America/El_Salvador") // 9 AM diario
    public void enviarRecordatoriosAutomaticos() {
        List<Prestamo> prestamosVigentes = prestamoRepository.findAll(); // Modificar según necesidad

        prestamosVigentes.forEach(prestamo -> {
            LocalDate hoy = LocalDate.now();
            long diasRestantes = ChronoUnit.DAYS.between(hoy, prestamo.getFechaFin());

            if (diasRestantes <= 3 && diasRestantes >= 0) {
                try {
                    emailService.enviarRecordatorioPrestamo(
                            prestamo.getCorreo(),
                            prestamo.getNombreLibro(),
                            prestamo.getNombreEstudiante(),
                            prestamo.getFechaFin()
                    );
                } catch (MessagingException e) {
                    System.err.println("Error enviando recordatorio: " + e.getMessage());
                }
            }
        });
    }
}