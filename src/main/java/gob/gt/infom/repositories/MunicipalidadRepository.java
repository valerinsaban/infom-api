package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Municipalidad;

public interface MunicipalidadRepository extends CrudRepository<Municipalidad, Integer> {
 
  Optional<Municipalidad> findOneByDepartamentoCodigoAndMunicipioCodigo(String codigo_departamento, String codigo_municipio);

  Iterable<Municipalidad> findAllByDepartamentoCodigo(String codigo_departamento);

}
