package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Recibo;

public interface ReciboRepository extends CrudRepository<Recibo, Integer> {
  
      List<Recibo> findAllByFechaBetween(String start, String end);

}
