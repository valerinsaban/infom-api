package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.ClasePrestamoGarantia;

public interface ClasePrestamoGarantiaRepository extends CrudRepository<ClasePrestamoGarantia, Integer> {

    List<ClasePrestamoGarantia> findAllByClasePrestamoId(Integer id_clase_prestamo);

}
