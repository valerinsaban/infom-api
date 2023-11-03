package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.OrdenPago;

public interface OrdenPagoRepository extends CrudRepository<OrdenPago, Integer> {
  
    Iterable<OrdenPago> findAllByPrestamoId(Integer id_prestamo);

    Optional<OrdenPago> countByFechaBetween(String start, String end);

    Iterable<OrdenPago> findAllByPrestamoIdAndFechaBetween(Integer id_prestamo, String start, String end);

}
