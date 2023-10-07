package gob.gt.infom.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import gob.gt.infom.models.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Integer> {

  List<Prestamo> findAllByEstado(String estado);

  List<Prestamo> findAllByFechaBetween(String start, String end);

  List<Prestamo> findAllByEstadoAndFechaBetween(String estado, String start, String end);

  Optional<Prestamo> countByEstadoAndFechaBetween(String estado, String start, String end);

  List<Prestamo> findAllByEstadoAndMunicipalidadId(String estado, Integer id_municipalidad);

  List<Prestamo> findAllByUsuarioId(Integer id_usuario);

  Optional<Prestamo> countByTipoPrestamoIdAndClasePrestamoId(Integer id_tipo_prestamo, Integer id_clase_prestamo);

  Optional<Prestamo> countByMunicipalidadId(Integer id_municipalidad);

}
