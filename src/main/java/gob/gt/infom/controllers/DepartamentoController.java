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

import gob.gt.infom.models.Departamento;
import gob.gt.infom.repositories.DepartamentoRepository;
import jakarta.validation.Valid;

@RestController
public class DepartamentoController {

  @Autowired
  private DepartamentoRepository repository;

  @GetMapping("/departamentos")
  public Iterable<Departamento> all() {
    return repository.findAll();
  }

  @GetMapping("/departamentos/{id}")
  public Optional<Departamento> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/departamentos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Departamento d) {
    Departamento departamento = Departamento.builder()
        .codigo(d.getCodigo())
        .nombre(d.getNombre())
        .build();
    repository.save(departamento);
    return ResponseController.success("Departamento Agregado Correctamente", departamento);
  }

  @PutMapping("/departamentos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Departamento d) {
    Optional<Departamento> data = repository.findById(id);
    if (data.isPresent()) {
      Departamento departamento = data.get();
      departamento.setCodigo(d.getCodigo());
      departamento.setNombre(d.getNombre());
      repository.save(departamento);
      return ResponseController.success("Departamento Actualizado Correctamente", departamento);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/departamentos/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Departamento> departamento = repository.findById(id);
    if (departamento.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Departamento Eliminado Correctamente", departamento);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
