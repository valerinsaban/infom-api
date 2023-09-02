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

import gob.gt.infom.models.Rol;
import gob.gt.infom.repositories.RolRepository;
import jakarta.validation.Valid;

@RestController
public class RolController {

  @Autowired
  private RolRepository repository;

  @GetMapping("/roles")
  public Iterable<Rol> all() {
    return repository.findAll();
  }

  @GetMapping("/roles/{id}")
  public Optional<Rol> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/roles")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Rol r) {
    Rol rol = Rol.builder()
        .nombre(r.getNombre())
        .color(r.getColor())
        .build();
    repository.save(rol);
    return ResponseController.success("Rol Agregado Correctamente", rol);
  }

  @PutMapping("/roles/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Rol r) {
    Optional<Rol> data = repository.findById(id);
    if (data.isPresent()) {
      Rol rol = data.get();
      rol.setNombre(r.getNombre());
      rol.setColor(r.getColor());
      repository.save(rol);
      return ResponseController.success("Rol Actualizado Correctamente", rol);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/roles/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Rol> rol = repository.findById(id);
    if (rol.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Rol Eliminado Correctamente", rol);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
