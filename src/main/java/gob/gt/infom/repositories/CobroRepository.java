package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Cobro;

public interface CobroRepository extends CrudRepository<Cobro, Integer> {
  
    Optional<Cobro> findTopByOrderByMesDesc();

    Optional<Cobro> findByMes(String mes);

}
