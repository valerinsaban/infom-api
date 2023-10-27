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

import gob.gt.infom.models.DestinoPrestamo;
import gob.gt.infom.repositories.DestinoPrestamoRepository;
import jakarta.validation.Valid;

@RestController
public class DestinoPrestamoController {

  @Autowired
  private DestinoPrestamoRepository repository;

  @GetMapping("/destinos_prestamos")
  public Iterable<DestinoPrestamo> all() {
    return repository.findAll();
  }

  @GetMapping("/destinos_prestamos/{id}")
  public Optional<DestinoPrestamo> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/destinos_prestamos/prestamo/{id_prestamo}")
  public Iterable<DestinoPrestamo> findAllByPrestamoId(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @PostMapping("/destinos_prestamos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid DestinoPrestamo d) {
    DestinoPrestamo destino = DestinoPrestamo.builder()
        .descripcion(d.getDescripcion())
        .monto(d.getMonto())
        .id_destino(d.getId_destino())
        .id_prestamo(d.getId_prestamo())
        .build();
    repository.save(destino);
    return ResponseController.success("DestinoPrestamo Agregado Correctamente", destino);
  }

  @PutMapping("/destinos_prestamos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody DestinoPrestamo d) {
    Optional<DestinoPrestamo> data = repository.findById(id);
    if (data.isPresent()) {
      DestinoPrestamo destino = data.get();
      destino.setDescripcion(d.getDescripcion());
      destino.setMonto(d.getMonto());
      destino.setId_destino(d.getId_destino());
      destino.setId_prestamo(d.getId_prestamo());
      repository.save(destino);
      return ResponseController.success("DestinoPrestamo Actualizado Correctamente", destino);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/destinos_prestamos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<DestinoPrestamo> destino = repository.findById(id);
    if (destino.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("DestinoPrestamo Eliminado Correctamente", destino);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
