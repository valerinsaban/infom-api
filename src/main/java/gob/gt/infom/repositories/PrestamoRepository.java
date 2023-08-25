package gob.gt.infom.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

  Iterable<Prestamo> findAllByEstadoAndFechaBetween(String estado, Date start, Date end);

}
