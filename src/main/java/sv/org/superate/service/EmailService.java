package sv.org.superate.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void enviarRecordatorioPrestamo(String to, String nombreLibro,
                                           String nombreEstudiante, LocalDate fechaFin) throws MessagingException {
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), fechaFin);

        Context context = new Context();
        context.setVariable("nombreEstudiante", nombreEstudiante);
        context.setVariable("nombreLibro", nombreLibro);
        context.setVariable("diasRestantes", diasRestantes);

        String contenidoHtml = templateEngine.process("email/recordatorio", context);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setTo(to);
            helper.setSubject("Recordatorio de préstamo de libro");
            helper.setText(contenidoHtml, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el email", e);
        }
    }
}
