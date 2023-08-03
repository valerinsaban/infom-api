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

import gob.gt.infom.models.TesoreroMunicipal;
import gob.gt.infom.repositories.TesoreroMunicipalRepository;
import jakarta.validation.Valid;

@RestController
public class TesoreroMunicipalController {

  @Autowired
  private TesoreroMunicipalRepository repository;

  @GetMapping("/tesoreros_municipales")
  public Iterable<TesoreroMunicipal> all() {
    return repository.findAll();
  }

  @GetMapping("/tesoreros_municipales/{id}")
  public Optional<TesoreroMunicipal> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/tesoreros_municipales")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid TesoreroMunicipal d) {
    TesoreroMunicipal tesorero_municipal = TesoreroMunicipal.builder()
        .codigo(d.getCodigo())
        .descripcion(d.getDescripcion())
        .build();
    repository.save(tesorero_municipal);
    return ResponseController.success("TesoreroMunicipal Agregado Correctamente", tesorero_municipal);
  }

  @PutMapping("/tesoreros_municipales/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TesoreroMunicipal d) {
    Optional<TesoreroMunicipal> data = repository.findById(id);
    if (data.isPresent()) {
      TesoreroMunicipal tesorero_municipal = data.get();
      tesorero_municipal.setCodigo(d.getCodigo());
      tesorero_municipal.setDescripcion(d.getDescripcion());
      repository.save(tesorero_municipal);
      return ResponseController.success("TesoreroMunicipal Actualizado Correctamente", tesorero_municipal);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/tesoreros_municipales/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<TesoreroMunicipal> tesorero_municipal = repository.findById(id);
    if (tesorero_municipal.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("TesoreroMunicipal Eliminado Correctamente", tesorero_municipal);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
