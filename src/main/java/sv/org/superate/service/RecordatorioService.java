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
    @Scheduled(fixedRate = 120000) // > 120,000 ms = 2 minutos
    @Scheduled(cron = "0 0 9 * * ?", zone = "America/El_Salvador") // 9 AM diario
    public void enviarRecordatoriosAutomaticos() {
        LocalDate hoy = LocalDate.now();

        // Solo préstamos que aún no vencen
        List<Prestamo> prestamosVigentes = prestamoRepository.findByFechaFinGreaterThanEqual(hoy);

        prestamosVigentes.forEach(prestamo -> {
            long diasRestantes = ChronoUnit.DAYS.between(hoy, prestamo.getFechaFin());

            // Si faltan 3 días o menos
            if (diasRestantes <= 3) {
                try {
                    emailService.enviarRecordatorioPrestamo(
                            prestamo.getCorreo(),
                            prestamo.getNombreLibro(),
                            prestamo.getNombreEstudiante(),
                            prestamo.getFechaFin()
                    );
                } catch (MessagingException e) {
                    System.err.println("Error enviando recordatorio a " + prestamo.getCorreo() + ": " + e.getMessage());
                }
            }
        });
    }
}