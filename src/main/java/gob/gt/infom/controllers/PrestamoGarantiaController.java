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

import gob.gt.infom.models.PrestamoGarantia;
import gob.gt.infom.repositories.PrestamoGarantiaRepository;
import jakarta.validation.Valid;

@RestController
public class PrestamoGarantiaController {

  @Autowired
  private PrestamoGarantiaRepository repository;

  @GetMapping("/prestamos_garantias/prestamo/{id_prestamo}")
  public List<PrestamoGarantia> one(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @PostMapping("/prestamos_garantias")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid PrestamoGarantia p) {
    PrestamoGarantia prestamo = PrestamoGarantia.builder()
        .monto(p.getMonto())
        .id_garantia(p.getId_garantia())
        .id_prestamo(p.getId_prestamo())
        .build();
    repository.save(prestamo);
    return ResponseController.success("Prestamo Garantia Agregado Correctamente", prestamo);
  }

  @PutMapping("/prestamos_garantias/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PrestamoGarantia p) {
    Optional<PrestamoGarantia> data = repository.findById(id);
    if (data.isPresent()) {
      PrestamoGarantia prestamo = data.get();
      prestamo.setMonto(p.getMonto());
      prestamo.setId_garantia(p.getId_garantia());
      prestamo.setId_prestamo(p.getId_prestamo());
      repository.save(prestamo);
      return ResponseController.success("Prestamo Garantia Actualizado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/prestamos_garantias/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<PrestamoGarantia> prestamo = repository.findById(id);
    if (prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Prestamo Garantia Eliminado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
