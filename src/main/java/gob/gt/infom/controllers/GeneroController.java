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

import gob.gt.infom.models.Genero;
import gob.gt.infom.repositories.GeneroRepository;
import jakarta.validation.Valid;

@RestController
public class GeneroController {

  @Autowired
  private GeneroRepository repository;

  @GetMapping("/generos")
  public Iterable<Genero> all() {
    return repository.findAll();
  }

  @GetMapping("/generos/{id}")
  public Optional<Genero> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/generos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Genero d) {
    Genero genero = Genero.builder()
        .codigo(d.getCodigo())
        .descripcion(d.getDescripcion())
        .build();
    repository.save(genero);
    return ResponseController.success("Genero Agregado Correctamente", genero);
  }

  @PutMapping("/generos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Genero d) {
    Optional<Genero> data = repository.findById(id);
    if (data.isPresent()) {
      Genero genero = data.get();
      genero.setCodigo(d.getCodigo());
      genero.setDescripcion(d.getDescripcion());
      repository.save(genero);
      return ResponseController.success("Genero Actualizado Correctamente", genero);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/generos/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Genero> genero = repository.findById(id);
    if (genero.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Genero Eliminado Correctamente", genero);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
