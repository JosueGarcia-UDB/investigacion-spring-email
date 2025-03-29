package sv.org.superate.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.org.superate.controller.DTO.request.PrestamoRequest;
import sv.org.superate.controller.DTO.response.PrestamoResponse;
import sv.org.superate.service.PrestamoService;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {
    private final PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearPrestamo(
            @Valid @RequestBody PrestamoRequest request) throws MessagingException {
        PrestamoResponse response = prestamoService.crearPrestamo(request);
        return ResponseEntity.ok(response);
    }
}