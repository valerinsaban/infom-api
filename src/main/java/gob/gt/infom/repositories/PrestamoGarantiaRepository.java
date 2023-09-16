package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.PrestamoGarantia;

public interface PrestamoGarantiaRepository extends CrudRepository<PrestamoGarantia, Integer> {

    List<PrestamoGarantia> findAllByPrestamoId(Integer id_prestamo);

}
