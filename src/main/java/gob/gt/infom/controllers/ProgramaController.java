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

import gob.gt.infom.models.Programa;
import gob.gt.infom.repositories.ProgramaRepository;
import jakarta.validation.Valid;

@RestController
public class ProgramaController {

  @Autowired
  private ProgramaRepository repository;

  @GetMapping("/programas")
  public Iterable<Programa> all() {
    return repository.findAll();
  }

  @GetMapping("/programas/{id}")
  public Optional<Programa> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/programas")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Programa g) {
    Programa programa = Programa.builder()
        .codigo(g.getCodigo())
        .nombre(g.getNombre())
        .siglas(g.getSiglas())
        .build();
    repository.save(programa);
    return ResponseController.success("Programa Agregado Correctamente", programa);
  }

  @PutMapping("/programas/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Programa g) {
    Optional<Programa> data = repository.findById(id);
    if (data.isPresent()) {
      Programa programa = data.get();
      programa.setCodigo(g.getCodigo());
      programa.setNombre(g.getNombre());
      programa.setSiglas(g.getSiglas());
      repository.save(programa);
      return ResponseController.success("Programa Actualizado Correctamente", programa);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/programas/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Programa> programa = repository.findById(id);
    if (programa.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Programa Eliminado Correctamente", programa);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
