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

import gob.gt.infom.models.Amortizacion;
import gob.gt.infom.repositories.AmortizacionRepository;
import jakarta.validation.Valid;

@RestController
public class AmortizacionController {

  @Autowired
  private AmortizacionRepository repository;

  @GetMapping("/amortizaciones")
  public Iterable<Amortizacion> all() {
    return repository.findAll();
  }

  @GetMapping("/amortizaciones/{id}")
  public Optional<Amortizacion> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @GetMapping("/amortizaciones/prestamo/{id_prestamo}")
  public Iterable<Amortizacion> findAllById_prestamo(@PathVariable Long id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @PostMapping("/amortizaciones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Amortizacion a) {
    Amortizacion amortizacion = Amortizacion.builder()
        .capital(a.getCapital())
        .intereses(a.getIntereses())
        .cuota(a.getCuota())
        .saldo(a.getSaldo())
        .fecha_inicio(a.getFecha_inicio())
        .fecha_fin(a.getFecha_fin())
        .dias(a.getDias())
        .id_prestamo(a.getId_prestamo())
        .build();
    repository.save(amortizacion);
    return ResponseController.success("Amortizacion Agregado Correctamente", amortizacion);
  }

  @PutMapping("/amortizaciones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Amortizacion a) {
    Optional<Amortizacion> data = repository.findById(id);
    if (data.isPresent()) {
      Amortizacion amortizacion = data.get();
      amortizacion.setCapital(a.getCapital());
      amortizacion.setIntereses(a.getIntereses());
      amortizacion.setCuota(a.getCuota());
      amortizacion.setSaldo(a.getSaldo());
      amortizacion.setFecha_inicio(a.getFecha_inicio());
      amortizacion.setFecha_fin(a.getFecha_fin());
      amortizacion.setDias(a.getDias());
      amortizacion.setId_prestamo(a.getId_prestamo());
      repository.save(amortizacion);
      return ResponseController.success("Amortizacion Actualizado Correctamente", amortizacion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/amortizaciones/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Amortizacion> amortizacion = repository.findById(id);
    if (amortizacion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Amortizacion Eliminado Correctamente", amortizacion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
