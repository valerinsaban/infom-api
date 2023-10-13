package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Amortizacion;


public interface AmortizacionRepository extends CrudRepository<Amortizacion, Integer> {

  Iterable<Amortizacion> findAllByPrestamoId(Integer id_prestamo);

  Iterable<Amortizacion> findAllByCobroId(Integer id_cobro);

}
