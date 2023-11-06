package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.AmortizacionDetalle;


public interface AmortizacionDetalleRepository extends CrudRepository<AmortizacionDetalle, Integer> {

  Iterable<AmortizacionDetalle> findAllByAmortizacionId(Integer id_amortizacion);

}
