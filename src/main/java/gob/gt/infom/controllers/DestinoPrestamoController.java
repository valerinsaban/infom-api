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
  public Optional<DestinoPrestamo> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/destinos_prestamos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid DestinoPrestamo d) {
    DestinoPrestamo destino_prestamo = DestinoPrestamo.builder()
        .codigo(d.getCodigo())
        .descripcion(d.getDescripcion())
        .build();
    repository.save(destino_prestamo);
    return ResponseController.success("DestinoPrestamo Agregado Correctamente", destino_prestamo);
  }

  @PutMapping("/destinos_prestamos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DestinoPrestamo d) {
    Optional<DestinoPrestamo> data = repository.findById(id);
    if (data.isPresent()) {
      DestinoPrestamo destino_prestamo = data.get();
      destino_prestamo.setCodigo(d.getCodigo());
      destino_prestamo.setDescripcion(d.getDescripcion());
      repository.save(destino_prestamo);
      return ResponseController.success("DestinoPrestamo Actualizado Correctamente", destino_prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/destinos_prestamos/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<DestinoPrestamo> destino_prestamo = repository.findById(id);
    if (destino_prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("DestinoPrestamo Eliminado Correctamente", destino_prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
