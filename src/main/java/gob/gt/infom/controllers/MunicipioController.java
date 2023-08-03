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

import gob.gt.infom.models.Municipio;
import gob.gt.infom.repositories.MunicipioRepository;
import jakarta.validation.Valid;

@RestController
public class MunicipioController {

  @Autowired
  private MunicipioRepository repository;

  @GetMapping("/municipios")
  public Iterable<Municipio> all() {
    return repository.findAll();
  }

  @GetMapping("/municipios/{id}")
  public Optional<Municipio> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/municipios")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Municipio m) {
    Municipio municipio = Municipio.builder()
        .codigo(m.getCodigo())
        .descripcion(m.getDescripcion())
        .id_departamento(m.getId_departamento())
        .departamento(m.getDepartamento())
        .build();
    repository.save(municipio);
    return ResponseController.success("Municipio Agregado Correctamente", municipio);
  }

  @PutMapping("/municipios/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Municipio m) {
    Optional<Municipio> data = repository.findById(id);
    if (data.isPresent()) {
      Municipio municipio = data.get();
      municipio.setCodigo(m.getCodigo());
      municipio.setDescripcion(m.getDescripcion());
      repository.save(municipio);
      return ResponseController.success("Municipio Actualizado Correctamente", municipio);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/municipios/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Municipio> municipio = repository.findById(id);
    if (municipio.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Municipio Eliminado Correctamente", municipio);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
