package gob.gt.infom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.ProgramaGarantia;

public interface ProgramaGarantiaRepository extends CrudRepository<ProgramaGarantia, Integer> {

    List<ProgramaGarantia> findAllByProgramaId(Integer id_programa);

}
