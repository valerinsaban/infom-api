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

import gob.gt.infom.models.Desembolso;
import gob.gt.infom.repositories.DesembolsoRepository;
import jakarta.validation.Valid;

@RestController
public class DesembolsoController {

  @Autowired
  private DesembolsoRepository repository;

  @GetMapping("/desembolsos")
  public Iterable<Desembolso> all() {
    return repository.findAll();
  }

  @GetMapping("/desembolsos/{id}")
  public Optional<Desembolso> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/desembolsos/prestamo/{id_prestamo}")
  public Iterable<Desembolso> findAllByPrestamoId(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @PostMapping("/desembolsos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Desembolso d) {
    Desembolso desembolso = Desembolso.builder()
        .numero(d.getNumero())
        .mes(d.getMes())
        .monto(d.getMonto())
        .id_prestamo(d.getId_prestamo())
        .build();
    repository.save(desembolso);
    return ResponseController.success("Desembolso Agregado Correctamente", desembolso);
  }

  @PutMapping("/desembolsos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Desembolso d) {
    Optional<Desembolso> data = repository.findById(id);
    if (data.isPresent()) {
      Desembolso desembolso = data.get();
      desembolso.setNumero(d.getNumero());
      desembolso.setMes(d.getMes());
      desembolso.setMonto(d.getMonto());
      desembolso.setId_prestamo(d.getId_prestamo());
      repository.save(desembolso);
      return ResponseController.success("Desembolso Actualizado Correctamente", desembolso);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/desembolsos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Desembolso> desembolso = repository.findById(id);
    if (desembolso.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Desembolso Eliminado Correctamente", desembolso);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
