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

import gob.gt.infom.models.Puesto;
import gob.gt.infom.repositories.PuestoRepository;
import jakarta.validation.Valid;

@RestController
public class PuestoController {

  @Autowired
  private PuestoRepository repository;

  @GetMapping("/puestos")
  public Iterable<Puesto> all() {
    return repository.findAll();
  }

  @GetMapping("/puestos/{id}")
  public Optional<Puesto> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/puestos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Puesto p) {
    Puesto puesto = Puesto.builder()
        .codigo(p.getCodigo())
        .nombre(p.getNombre())
        .build();
    repository.save(puesto);
    return ResponseController.success("Puesto Agregado Correctamente", puesto);
  }

  @PutMapping("/puestos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Puesto p) {
    Optional<Puesto> data = repository.findById(id);
    if (data.isPresent()) {
      Puesto puesto = data.get();
      puesto.setCodigo(p.getCodigo());
      puesto.setNombre(p.getNombre());
      repository.save(puesto);
      return ResponseController.success("Puesto Actualizado Correctamente", puesto);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/puestos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Puesto> puesto = repository.findById(id);
    if (puesto.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Puesto Eliminado Correctamente", puesto);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
