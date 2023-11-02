package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer> {
  
    List<Factura> findAllByFechaBetween(String start, String end);

}
