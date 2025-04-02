package sv.org.superate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.superate.repository.domain.Prestamo;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // Consulta mejorada para préstamos vigentes
    List<Prestamo> findByFechaFinGreaterThanEqual(LocalDate fechaActual);
}
