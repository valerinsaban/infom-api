package gob.gt.infom.controllers;

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
  public Optional<Permiso> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/permisos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Permiso r) {
    Permiso permiso = Permiso.builder()
        .nombre(r.getNombre())
        .id_rol(r.getId_rol())
        .id_menu(r.getId_menu())
        .build();
    repository.save(permiso);
    return ResponseController.success("Permiso Agregado Correctamente", permiso);
  }

  @PutMapping("/permisos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Permiso r) {
    Optional<Permiso> data = repository.findById(id);
    if (data.isPresent()) {
      Permiso permiso = data.get();
      permiso.setNombre(r.getNombre());
      permiso.setId_rol(r.getId_rol());
      permiso.setId_menu(r.getId_menu());
      repository.save(permiso);
      return ResponseController.success("Permiso Actualizado Correctamente", permiso);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/permisos/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Permiso> permiso = repository.findById(id);
    if (permiso.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Permiso Eliminado Correctamente", permiso);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
