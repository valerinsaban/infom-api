package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

  List<Prestamo> findAllByFechaBetween(String start, String end);

  List<Prestamo> findAllByEstadoAndFechaBetween(String estado, String start, String end);

}
