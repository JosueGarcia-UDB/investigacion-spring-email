package sv.org.superate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.org.superate.repository.domain.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
