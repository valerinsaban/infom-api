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

import gob.gt.infom.models.Destino;
import gob.gt.infom.repositories.DestinoRepository;
import jakarta.validation.Valid;

@RestController
public class DestinoController {

  @Autowired
  private DestinoRepository repository;

  @GetMapping("/destinos")
  public Iterable<Destino> all() {
    return repository.findAll();
  }

  @GetMapping("/destinos/{id}")
  public Optional<Destino> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/destinos/codigo/{id}")
  public Optional<Destino> findByCodigo(@PathVariable String codigo) {
    return repository.findByCodigo(codigo);
  }

  @PostMapping("/destinos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Destino p) {
    Destino destino = Destino.builder()
        .codigo(p.getCodigo())
        .nombre(p.getNombre())
        .build();
    repository.save(destino);
    return ResponseController.success("Destino Agregado Correctamente", destino);
  }

  @PutMapping("/destinos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Destino p) {
    Optional<Destino> data = repository.findById(id);
    if (data.isPresent()) {
      Destino destino = data.get();
      destino.setCodigo(p.getCodigo());
      destino.setNombre(p.getNombre());
      repository.save(destino);
      return ResponseController.success("Destino Actualizado Correctamente", destino);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/destinos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Destino> destino = repository.findById(id);
    if (destino.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Destino Eliminado Correctamente", destino);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
