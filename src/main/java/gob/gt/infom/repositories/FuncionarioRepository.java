package gob.gt.infom.repositories;

import gob.gt.infom.models.Funcionario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

  Iterable<Funcionario> findAllByMunicipalidadId(Integer id_municipalidad);

  Optional<Funcionario> findByMunicipalidadIdAndEstado(Integer id_municipalidad, String estado);

}
