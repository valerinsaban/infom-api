package gob.gt.infom.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gob.gt.infom.models.Permiso;
import gob.gt.infom.repositories.PermisoRepository;
import jakarta.validation.Valid;

@RestController
public class PermisoController {

  @Autowired
  private PermisoRepository repository;

  @GetMapping("/permisos")
  public Iterable<Permiso> all() {
    return repository.findAll();
  }

  @GetMapping("/permisos/{id}")
  public Optional<Permiso> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/permisos/{accion}/{id_rol}/{id_menu}/{id_submenu}")
  public List<Permiso> oneByRol(@PathVariable String accion, Integer id_rol, Integer id_menu, Integer id_submenu) {
    return repository.findOneByAccionAndRolIdAndMenuIdAndSubmenuId(accion, id_rol, id_menu, id_submenu);
  }

  @GetMapping("/permisos/rol/{id_rol}")
  public List<Permiso> oneAllByRol(@PathVariable Integer id_rol) {
    return repository.findAllByRolId(id_rol);
  }

  @PostMapping("/permisos")
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody @Valid Permiso r) {
    List<Permiso> data = repository.findOneByAccionAndRolIdAndMenuIdAndSubmenuId(r.getAccion(), r.getId_rol(),
        r.getId_menu(), r.getId_submenu());
    if (data.isEmpty()) {
      Permiso permiso = Permiso.builder()
          .accion(r.getAccion())
          .id_rol(r.getId_rol())
          .id_menu(r.getId_menu())
          .id_submenu(r.getId_submenu())
          .build();
      repository.save(permiso);
      return ResponseController.success("Permiso habilitado", permiso);
    } else {
      Integer id = Integer.valueOf(data.get(0).getId());
      repository.deleteById(id);
      return ResponseController.success("Permiso deshabilitado", data);
    }
  }

  @PutMapping("/permisos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Permiso r) {
    Optional<Permiso> data = repository.findById(id);
    if (data.isPresent()) {
      Permiso permiso = data.get();
      permiso.setAccion(r.getAccion());
      permiso.setId_rol(r.getId_rol());
      permiso.setId_menu(r.getId_menu());
      permiso.setId_submenu(r.getId_submenu());
      repository.save(permiso);
      return ResponseController.success("Permiso Actualizado Correctamente", permiso);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/permisos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Permiso> permiso = repository.findById(id);
    if (permiso.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Permiso Eliminado Correctamente", permiso);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
