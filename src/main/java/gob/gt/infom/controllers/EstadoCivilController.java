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

import gob.gt.infom.models.EstadoCivil;
import gob.gt.infom.repositories.EstadoCivilRepository;
import jakarta.validation.Valid;

@RestController
public class EstadoCivilController {

  @Autowired
  private EstadoCivilRepository repository;

  @GetMapping("/estados_civiles")
  public Iterable<EstadoCivil> all() {
    return repository.findAll();
  }

  @GetMapping("/estados_civiles/{id}")
  public Optional<EstadoCivil> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/estados_civiles")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid EstadoCivil e) {
    EstadoCivil estado_civil = EstadoCivil.builder()
        .codigo(e.getCodigo())
        .nombre(e.getNombre())
        .build();
    repository.save(estado_civil);
    return ResponseController.success("EstadoCivil Agregado Correctamente", estado_civil);
  }

  @PutMapping("/estados_civiles/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody EstadoCivil e) {
    Optional<EstadoCivil> data = repository.findById(id);
    if (data.isPresent()) {
      EstadoCivil estado_civil = data.get();
      estado_civil.setCodigo(e.getCodigo());
      estado_civil.setNombre(e.getNombre());
      repository.save(estado_civil);
      return ResponseController.success("EstadoCivil Actualizado Correctamente", estado_civil);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/estados_civiles/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<EstadoCivil> estado_civil = repository.findById(id);
    if (estado_civil.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("EstadoCivil Eliminado Correctamente", estado_civil);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
