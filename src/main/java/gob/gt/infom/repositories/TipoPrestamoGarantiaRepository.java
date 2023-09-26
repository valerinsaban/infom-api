package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.TipoPrestamoGarantia;

public interface TipoPrestamoGarantiaRepository extends CrudRepository<TipoPrestamoGarantia, Integer> {

    List<TipoPrestamoGarantia> findAllByTipoPrestamoId(Integer id_tipo_prestamo);

}
