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
    Desembolso destino = Desembolso.builder()
        .numero(d.getNumero())
        .mes(d.getMes())
        .monto(d.getMonto())
        .id_prestamo(d.getId_prestamo())
        .build();
    repository.save(destino);
    return ResponseController.success("Desembolso Agregado Correctamente", destino);
  }

  @PutMapping("/desembolsos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Desembolso d) {
    Optional<Desembolso> data = repository.findById(id);
    if (data.isPresent()) {
      Desembolso destino = data.get();
      destino.setNumero(d.getNumero());
      destino.setMes(d.getMes());
      destino.setMonto(d.getMonto());
      destino.setId_prestamo(d.getId_prestamo());
      repository.save(destino);
      return ResponseController.success("Desembolso Actualizado Correctamente", destino);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/desembolsos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Desembolso> destino = repository.findById(id);
    if (destino.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Desembolso Eliminado Correctamente", destino);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
