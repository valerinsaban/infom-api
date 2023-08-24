package gob.gt.infom.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {
  
    List<Prestamo> findAllByFechaBetween(Date fecha_inicio, Date fecha_fin);

}
