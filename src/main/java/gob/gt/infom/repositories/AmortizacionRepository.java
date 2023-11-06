package gob.gt.infom.repositories;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Amortizacion;

public interface AmortizacionRepository extends CrudRepository<Amortizacion, Integer> {

  Iterable<Amortizacion> findAllByCobroId(Integer id_cobro);

  Iterable<Amortizacion> findAllByProgramaId(Integer id_programa);

  Iterable<Amortizacion> findAllByProgramaIdAndMes(Integer id_programa, String mes);

  Iterable<Amortizacion> findAllByPrestamoId(Integer id_prestamo);

  Iterable<Amortizacion> findAllByPrestamoIdAndMes(Integer id_prestamo, String mes);

}
