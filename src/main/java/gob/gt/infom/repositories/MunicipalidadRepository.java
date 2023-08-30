package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Municipalidad;

public interface MunicipalidadRepository extends CrudRepository<Municipalidad, Long> {
 
  Optional<Municipalidad> findOneByDepartamentoCodigoAndMunicipioCodigo(String codigo_departamento, String codigo_municipio);

}
