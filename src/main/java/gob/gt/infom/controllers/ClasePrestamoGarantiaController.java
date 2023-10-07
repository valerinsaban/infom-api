package gob.gt.infom.controllers;

import java.util.List;
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

import gob.gt.infom.models.ClasePrestamoGarantia;
import gob.gt.infom.repositories.ClasePrestamoGarantiaRepository;
import jakarta.validation.Valid;

@RestController
public class ClasePrestamoGarantiaController {

  @Autowired
  private ClasePrestamoGarantiaRepository repository;

  @GetMapping("/clases_prestamos_garantias/clase_prestamo/{id_clase_prestamo}")
  public List<ClasePrestamoGarantia> one(@PathVariable Integer id_clase_prestamo) {
    return repository.findAllByClasePrestamoId(id_clase_prestamo);
  }

  @PostMapping("/clases_prestamos_garantias")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid ClasePrestamoGarantia p) {
    ClasePrestamoGarantia prestamo = ClasePrestamoGarantia.builder()
        .id_garantia(p.getId_garantia())
        .id_clase_prestamo(p.getId_clase_prestamo())
        .build();
    repository.save(prestamo);
    return ResponseController.success("Prestamo Garantia Agregado Correctamente", prestamo);
  }

  @PutMapping("/clases_prestamos_garantias/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ClasePrestamoGarantia p) {
    Optional<ClasePrestamoGarantia> data = repository.findById(id);
    if (data.isPresent()) {
      ClasePrestamoGarantia prestamo = data.get();
      prestamo.setId_garantia(p.getId_garantia());
      prestamo.setId_clase_prestamo(p.getId_clase_prestamo());
      repository.save(prestamo);
      return ResponseController.success("Prestamo Garantia Actualizado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/clases_prestamos_garantias/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<ClasePrestamoGarantia> prestamo = repository.findById(id);
    if (prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Prestamo Garantia Eliminado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
