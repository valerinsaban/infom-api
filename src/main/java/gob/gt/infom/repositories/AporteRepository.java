package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Aporte;

public interface AporteRepository extends CrudRepository<Aporte, Integer> {

  Iterable<Aporte> findAllByMesBetweenAndCodigoDepartamentoAndCodigoMunicipio(String mes_inicio, String mes_fin, String codigo_departamento, String codigo_municipio);

  Iterable<Aporte> findAllByMes(String mes);

  Optional<Aporte> findTopByCodigoDepartamentoAndCodigoMunicipioOrderByMesDesc(String codigo_departamento, String codigo_municipio);

}
