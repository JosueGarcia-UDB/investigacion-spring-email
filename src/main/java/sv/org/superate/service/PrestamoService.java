package sv.org.superate.service;

import jakarta.mail.MessagingException;
import sv.org.superate.controller.DTO.response.PrestamoResponse;
import sv.org.superate.controller.DTO.request.PrestamoRequest;

public interface PrestamoService {
    PrestamoResponse crearPrestamo(PrestamoRequest request) throws MessagingException;
}
