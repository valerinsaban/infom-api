package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Destino;

public interface DestinoRepository extends CrudRepository<Destino, Integer> {

  Optional<Destino> findByCodigo(String codigo);

}
