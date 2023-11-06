package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Movimiento;


public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {

  Iterable<Movimiento> findAllByPrestamoId(Integer id_prestamo);

  Iterable<Movimiento> findAllByReciboId(Integer id_recibo);

  Iterable<Movimiento> findAllByOrdenPagoId(Integer id_cobro);

  Optional<Movimiento> findTopByPrestamoIdOrderByIdDesc(Integer id_prestamo);

}
