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

import gob.gt.infom.models.Resolucion;
import gob.gt.infom.repositories.ResolucionRepository;
import jakarta.validation.Valid;

@RestController
public class ResolucionController {

  @Autowired
  private ResolucionRepository repository;

  @GetMapping("/resoluciones")
  public Iterable<Resolucion> all() {
    return repository.findAll();
  }

  @GetMapping("/resoluciones/{id}")
  public Optional<Resolucion> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/resoluciones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Resolucion r) {
    Resolucion resolucion = Resolucion.builder()
        .nombre(r.getNombre())
        .numero(r.getNumero())
        .fecha(r.getFecha())
        .articulos(r.getArticulos())
        .build();
    repository.save(resolucion);
    return ResponseController.success("Resolucion Agregado Correctamente", resolucion);
  }

  @PutMapping("/resoluciones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Resolucion r) {
    Optional<Resolucion> data = repository.findById(id);
    if (data.isPresent()) {
      Resolucion resolucion = data.get();
      resolucion.setNombre(r.getNombre());
      resolucion.setNumero(r.getNumero());
      resolucion.setFecha(r.getFecha());
      resolucion.setArticulos(r.getArticulos());
      repository.save(resolucion);
      return ResponseController.success("Resolucion Actualizado Correctamente", resolucion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/resoluciones/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Resolucion> resolucion = repository.findById(id);
    if (resolucion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Resolucion Eliminado Correctamente", resolucion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
