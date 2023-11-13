package gob.gt.infom.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Servicio;

public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

  List<Servicio> findAllByEstado(String estado);

  List<Servicio> findAllByFechaBetween(String start, String end);

  List<Servicio> findAllByEstadoAndFechaBetween(String estado, String start, String end);

  Optional<Servicio> countByFechaBetween(String start, String end);

  Optional<Servicio> countByEstadoAndFechaBetween(String estado, String start, String end);

  List<Servicio> findAllByEstadoAndMunicipalidadId(String estado, Integer id_municipalidad);

  List<Servicio> findAllByEstadoAndProgramaId(String estado, Integer id_programa);

  List<Servicio> findAllByEstadoAndMunicipalidadIdAndProgramaId(String estado, Integer id_municipalidad, Integer id_programa);

  List<Servicio> findAllByUsuarioId(Integer id_usuario);

  Optional<Servicio> countByTipoServicioIdAndProgramaIdAndFechaBetween(Integer id_tipo_servicio, Integer id_programa, String start, String end);

  Optional<Servicio> countByMunicipalidadId(Integer id_municipalidad);

}
