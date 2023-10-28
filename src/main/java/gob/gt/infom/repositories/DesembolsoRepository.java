package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Desembolso;

public interface DesembolsoRepository extends CrudRepository<Desembolso, Integer> {
  
    Iterable<Desembolso> findAllByPrestamoId(Integer id_prestamo);

}
