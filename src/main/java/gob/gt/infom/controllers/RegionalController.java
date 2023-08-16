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

import gob.gt.infom.models.Regional;
import gob.gt.infom.repositories.RegionalRepository;
import jakarta.validation.Valid;

@RestController
public class RegionalController {

  @Autowired
  private RegionalRepository repository;

  @GetMapping("/regionales")
  public Iterable<Regional> all() {
    return repository.findAll();
  }

  @GetMapping("/regionales/{id}")
  public Optional<Regional> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/regionales")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Regional r) {
    Regional regional = Regional.builder()
        .codigo(r.getCodigo())
        .nombre(r.getNombre())
        .direccion(r.getDireccion())
        .telefono(r.getTelefono())
        .correo(r.getCorreo())
        .encargado(r.getEncargado())
        .build();
    repository.save(regional);
    return ResponseController.success("Regional Agregada Correctamente", regional);
  }

  @PutMapping("/regionales/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Regional r) {
    Optional<Regional> data = repository.findById(id);
    if (data.isPresent()) {
      Regional regional = data.get();
      regional.setCodigo(r.getCodigo());
      regional.setNombre(r.getNombre());
      regional.setDireccion(r.getDireccion());
      regional.setTelefono(r.getTelefono());
      regional.setCorreo(r.getCorreo());
      regional.setEncargado(r.getEncargado());
      repository.save(regional);
      return ResponseController.success("Regional Actualizada Correctamente", regional);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/regionales/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Regional> regional = repository.findById(id);
    if (regional.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Regional Eliminada Correctamente", regional);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
