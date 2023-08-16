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

import gob.gt.infom.models.Profesion;
import gob.gt.infom.repositories.ProfesionRepository;
import jakarta.validation.Valid;

@RestController
public class ProfesionController {

  @Autowired
  private ProfesionRepository repository;

  @GetMapping("/profesiones")
  public Iterable<Profesion> all() {
    return repository.findAll();
  }

  @GetMapping("/profesiones/{id}")
  public Optional<Profesion> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/profesiones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Profesion p) {
    Profesion profesion = Profesion.builder()
        .codigo(p.getCodigo())
        .nombre(p.getNombre())
        .build();
    repository.save(profesion);
    return ResponseController.success("Profesion Agregado Correctamente", profesion);
  }

  @PutMapping("/profesiones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Profesion p) {
    Optional<Profesion> data = repository.findById(id);
    if (data.isPresent()) {
      Profesion profesion = data.get();
      profesion.setCodigo(p.getCodigo());
      profesion.setNombre(p.getNombre());
      repository.save(profesion);
      return ResponseController.success("Profesion Actualizado Correctamente", profesion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/profesiones/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Profesion> profesion = repository.findById(id);
    if (profesion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Profesion Eliminado Correctamente", profesion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
