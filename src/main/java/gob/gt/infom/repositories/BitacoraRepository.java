package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Bitacora;

public interface BitacoraRepository extends CrudRepository<Bitacora, Integer> {
  
    Iterable<Bitacora> findAllByFechaBetween(String fecha_inicio, String fecha_fin);

}
