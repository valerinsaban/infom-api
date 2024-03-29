package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.DestinoPrestamo;

public interface DestinoPrestamoRepository extends CrudRepository<DestinoPrestamo, Integer> {
  
    Iterable<DestinoPrestamo> findAllByPrestamoId(Integer id_prestamo);

}
