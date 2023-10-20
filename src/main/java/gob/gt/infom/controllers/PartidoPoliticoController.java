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

import gob.gt.infom.models.PartidoPolitico;
import gob.gt.infom.repositories.PartidoPoliticoRepository;
import jakarta.validation.Valid;

@RestController
public class PartidoPoliticoController {

  @Autowired
  private PartidoPoliticoRepository repository;

  @GetMapping("/partidos_politicos")
  public Iterable<PartidoPolitico> all() {
    return repository.findAll();
  }

  @GetMapping("/partidos_politicos/{id}")
  public Optional<PartidoPolitico> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/partidos_politicos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid PartidoPolitico p) {
    PartidoPolitico partido_politico = PartidoPolitico.builder()
        .codigo(p.getCodigo())
        .nombre(p.getNombre())
        .build();
    repository.save(partido_politico);
    return ResponseController.success("PartidoPolitico Agregado Correctamente", partido_politico);
  }

  @PutMapping("/partidos_politicos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PartidoPolitico p) {
    Optional<PartidoPolitico> data = repository.findById(id);
    if (data.isPresent()) {
      PartidoPolitico partido_politico = data.get();
      partido_politico.setCodigo(p.getCodigo());
      partido_politico.setNombre(p.getNombre());
      repository.save(partido_politico);
      return ResponseController.success("PartidoPolitico Actualizado Correctamente", partido_politico);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/partidos_politicos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<PartidoPolitico> partido_politico = repository.findById(id);
    if (partido_politico.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("PartidoPolitico Eliminado Correctamente", partido_politico);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
