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

import gob.gt.infom.models.TipoPrestamoGarantia;
import gob.gt.infom.repositories.TipoPrestamoGarantiaRepository;
import jakarta.validation.Valid;

@RestController
public class TipoPrestamoGarantiaController {

  @Autowired
  private TipoPrestamoGarantiaRepository repository;

  @GetMapping("/tipos_prestamos_garantias/tipo_prestamo/{id_tipo_prestamo}")
  public List<TipoPrestamoGarantia> one(@PathVariable Integer id_tipo_prestamo) {
    return repository.findAllByTipoPrestamoId(id_tipo_prestamo);
  }

  @PostMapping("/tipos_prestamos_garantias")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid TipoPrestamoGarantia p) {
    TipoPrestamoGarantia prestamo = TipoPrestamoGarantia.builder()
        .id_garantia(p.getId_garantia())
        .id_tipo_prestamo(p.getId_tipo_prestamo())
        .build();
    repository.save(prestamo);
    return ResponseController.success("Prestamo Garantia Agregado Correctamente", prestamo);
  }

  @PutMapping("/tipos_prestamos_garantias/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody TipoPrestamoGarantia p) {
    Optional<TipoPrestamoGarantia> data = repository.findById(id);
    if (data.isPresent()) {
      TipoPrestamoGarantia prestamo = data.get();
      prestamo.setId_garantia(p.getId_garantia());
      prestamo.setId_tipo_prestamo(p.getId_tipo_prestamo());
      repository.save(prestamo);
      return ResponseController.success("Prestamo Garantia Actualizado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/tipos_prestamos_garantias/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<TipoPrestamoGarantia> prestamo = repository.findById(id);
    if (prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Prestamo Garantia Eliminado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
