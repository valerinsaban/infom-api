package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.ReciboDetalle;

public interface ReciboDetalleRepository extends CrudRepository<ReciboDetalle, Integer> {
  
    List<ReciboDetalle> findAllByReciboId(Integer id_recibo);


}
