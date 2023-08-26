package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Amortizacion;


public interface AmortizacionRepository extends CrudRepository<Amortizacion, Long> {

  Iterable<Amortizacion> findAllByPrestamoId(Long id_prestamo);

}
