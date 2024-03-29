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

import gob.gt.infom.models.TipoPrestamo;
import gob.gt.infom.repositories.TipoPrestamoRepository;
import jakarta.validation.Valid;

@RestController
public class TipoPrestamoController {

  @Autowired
  private TipoPrestamoRepository repository;

  @GetMapping("/tipos_prestamos")
  public Iterable<TipoPrestamo> all() {
    return repository.findAll();
  }

  @GetMapping("/tipos_prestamos/{id}")
  public Optional<TipoPrestamo> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/tipos_prestamos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid TipoPrestamo t) {
    TipoPrestamo tipo_prestamo = TipoPrestamo.builder()
        .nombre(t.getNombre())
        .siglas(t.getSiglas())
        .monto_min(t.getMonto_min())
        .monto_max(t.getMonto_max())
        .centro_costo(t.getCentro_costo())
        .producto(t.getProducto())
        .subproducto(t.getSubproducto())
        .build();
    repository.save(tipo_prestamo);
    return ResponseController.success("TipoPrestamo Agregada Correctamente", tipo_prestamo);
  }

  @PutMapping("/tipos_prestamos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody TipoPrestamo t) {
    Optional<TipoPrestamo> data = repository.findById(id);
    if (data.isPresent()) {
      TipoPrestamo tipo_prestamo = data.get();
      tipo_prestamo.setNombre(t.getNombre());
      tipo_prestamo.setSiglas(t.getSiglas());
      tipo_prestamo.setMonto_min(t.getMonto_min());
      tipo_prestamo.setMonto_max(t.getMonto_max());
      tipo_prestamo.setCentro_costo(t.getCentro_costo());
      tipo_prestamo.setProducto(t.getProducto());
      tipo_prestamo.setSubproducto(t.getSubproducto());
      repository.save(tipo_prestamo);
      return ResponseController.success("TipoPrestamo Actualizada Correctamente", tipo_prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/tipos_prestamos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<TipoPrestamo> tipo_prestamo = repository.findById(id);
    if (tipo_prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("TipoPrestamo Eliminada Correctamente", tipo_prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
