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

import gob.gt.infom.models.ClasePrestamo;
import gob.gt.infom.repositories.ClasePrestamoRepository;
import jakarta.validation.Valid;

@RestController
public class ClasePrestamoController {

  @Autowired
  private ClasePrestamoRepository repository;

  @GetMapping("/clases_prestamos")
  public Iterable<ClasePrestamo> all() {
    return repository.findAll();
  }

  @GetMapping("/clases_prestamos/{id}")
  public Optional<ClasePrestamo> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/clases_prestamos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid ClasePrestamo g) {
    ClasePrestamo clase_prestamo = ClasePrestamo.builder()
        .codigo(g.getCodigo())
        .nombre(g.getNombre())
        .monto_min(g.getMonto_min())
        .monto_max(g.getMonto_max())
        .build();
    repository.save(clase_prestamo);
    return ResponseController.success("ClasePrestamo Agregada Correctamente", clase_prestamo);
  }

  @PutMapping("/clases_prestamos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ClasePrestamo g) {
    Optional<ClasePrestamo> data = repository.findById(id);
    if (data.isPresent()) {
      ClasePrestamo clase_prestamo = data.get();
      clase_prestamo.setCodigo(g.getCodigo());
      clase_prestamo.setNombre(g.getNombre());
      clase_prestamo.setMonto_min(g.getMonto_min());
      clase_prestamo.setMonto_max(g.getMonto_max());
      repository.save(clase_prestamo);
      return ResponseController.success("ClasePrestamo Actualizada Correctamente", clase_prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/clases_prestamos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<ClasePrestamo> clase_prestamo = repository.findById(id);
    if (clase_prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("ClasePrestamo Eliminada Correctamente", clase_prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
