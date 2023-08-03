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

import gob.gt.infom.models.OficinaRegional;
import gob.gt.infom.repositories.OficinaRegionalRepository;
import jakarta.validation.Valid;

@RestController
public class OficinaRegionalController {

  @Autowired
  private OficinaRegionalRepository repository;

  @GetMapping("/oficinas_regionales")
  public Iterable<OficinaRegional> all() {
    return repository.findAll();
  }

  @GetMapping("/oficinas_regionales/{id}")
  public Optional<OficinaRegional> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/oficinas_regionales")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid OficinaRegional d) {
    OficinaRegional oficina_regional = OficinaRegional.builder()
        .codigo(d.getCodigo())
        .descripcion(d.getDescripcion())
        .build();
    repository.save(oficina_regional);
    return ResponseController.success("OficinaRegional Agregado Correctamente", oficina_regional);
  }

  @PutMapping("/oficinas_regionales/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody OficinaRegional d) {
    Optional<OficinaRegional> data = repository.findById(id);
    if (data.isPresent()) {
      OficinaRegional oficina_regional = data.get();
      oficina_regional.setCodigo(d.getCodigo());
      oficina_regional.setDescripcion(d.getDescripcion());
      repository.save(oficina_regional);
      return ResponseController.success("OficinaRegional Actualizado Correctamente", oficina_regional);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/oficinas_regionales/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<OficinaRegional> oficina_regional = repository.findById(id);
    if (oficina_regional.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("OficinaRegional Eliminado Correctamente", oficina_regional);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
