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

import gob.gt.infom.models.Municipalidad;
import gob.gt.infom.repositories.MunicipalidadRepository;
import jakarta.validation.Valid;

@RestController
public class MunicipalidadController {

  @Autowired
  private MunicipalidadRepository repository;

  @GetMapping("/municipalidades")
  public Iterable<Municipalidad> all() {
    return repository.findAll();
  }

  @GetMapping("/municipalidades/{id}")
  public Optional<Municipalidad> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/municipalidades")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Municipalidad m) {
    Municipalidad municipalidad = Municipalidad.builder()
        .id_departamento(m.getId_departamento())
        .id_municipio(m.getId_municipio())
        .direccion(m.getDireccion())
        .build();
    repository.save(municipalidad);
    return ResponseController.success("Municipalidad Agregado Correctamente", municipalidad);
  }

  @PutMapping("/municipalidades/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Municipalidad m) {
    Optional<Municipalidad> data = repository.findById(id);
    if (data.isPresent()) {
      Municipalidad municipalidad = data.get();
      municipalidad.setId_departamento(m.getId_departamento());
      municipalidad.setId_municipio(m.getId_municipio());
      municipalidad.setDireccion(m.getDireccion());
      repository.save(municipalidad);
      return ResponseController.success("Municipalidad Actualizado Correctamente", municipalidad);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/municipalidades/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Municipalidad> municipalidad = repository.findById(id);
    if (municipalidad.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Municipalidad Eliminado Correctamente", municipalidad);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
