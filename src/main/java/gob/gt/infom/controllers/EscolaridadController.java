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

import gob.gt.infom.models.Escolaridad;
import gob.gt.infom.repositories.EscolaridadRepository;
import jakarta.validation.Valid;

@RestController
public class EscolaridadController {

  @Autowired
  private EscolaridadRepository repository;

  @GetMapping("/escolaridades")
  public Iterable<Escolaridad> all() {
    return repository.findAll();
  }

  @GetMapping("/escolaridades/{id}")
  public Optional<Escolaridad> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/escolaridades")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Escolaridad d) {
    Escolaridad escolaridad = Escolaridad.builder()
        .codigo(d.getCodigo())
        .descripcion(d.getDescripcion())
        .build();
    repository.save(escolaridad);
    return ResponseController.success("Escolaridad Agregada Correctamente", escolaridad);
  }

  @PutMapping("/escolaridades/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Escolaridad d) {
    Optional<Escolaridad> data = repository.findById(id);
    if (data.isPresent()) {
      Escolaridad escolaridad = data.get();
      escolaridad.setCodigo(d.getCodigo());
      escolaridad.setDescripcion(d.getDescripcion());
      repository.save(escolaridad);
      return ResponseController.success("Escolaridad Actualizada Correctamente", escolaridad);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/escolaridades/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Escolaridad> escolaridad = repository.findById(id);
    if (escolaridad.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Escolaridad Eliminada Correctamente", escolaridad);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
