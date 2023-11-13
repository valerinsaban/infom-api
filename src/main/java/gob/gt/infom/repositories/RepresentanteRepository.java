package gob.gt.infom.repositories;

import gob.gt.infom.models.Representante;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RepresentanteRepository extends CrudRepository<Representante, Integer> {

  Optional<Representante> findByEstado(String estado);

}
