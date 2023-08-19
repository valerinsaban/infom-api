package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Solicitud;

public interface SolicitudRepository extends CrudRepository<Solicitud, Long> {
  
}
