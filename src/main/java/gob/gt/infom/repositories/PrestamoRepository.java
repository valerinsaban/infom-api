package gob.gt.infom.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import gob.gt.infom.models.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

  List<Prestamo> findAllByFechaBetween(String start, String end);

  List<Prestamo> findAllByEstadoAndFechaBetween(String estado, String start, String end);

  Optional<Prestamo> countByEstadoAndFechaBetween(String estado, String start, String end);

}
