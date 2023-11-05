package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Proyeccion;


public interface ProyeccionRepository extends CrudRepository<Proyeccion, Integer> {

  Iterable<Proyeccion> findAllByPrestamoId(Integer id_prestamo);
  
  Optional<Proyeccion> deleteByPrestamoId(Integer id_prestamo);

}
