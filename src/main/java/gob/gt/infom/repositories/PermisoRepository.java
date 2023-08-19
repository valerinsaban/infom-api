package gob.gt.infom.repositories;

import gob.gt.infom.models.Permiso;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PermisoRepository extends CrudRepository<Permiso, Long> {

  List<Permiso> findOneByAccionAndRolIdAndMenuIdAndSubmenuId(String accion, Integer id_rol, Integer id_menu, Integer id_submenu);

}
