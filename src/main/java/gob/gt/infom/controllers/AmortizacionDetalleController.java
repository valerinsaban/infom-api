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

import gob.gt.infom.models.AmortizacionDetalle;
import gob.gt.infom.repositories.AmortizacionDetalleRepository;
import jakarta.validation.Valid;

@RestController
public class AmortizacionDetalleController {

  @Autowired
  private AmortizacionDetalleRepository repository;

  @GetMapping("/amortizaciones_detalles")
  public Iterable<AmortizacionDetalle> all() {
    return repository.findAll();
  }

  @GetMapping("/amortizaciones_detalles/{id}")
  public Optional<AmortizacionDetalle> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/amortizaciones_detalles/amortizacion/{id_amortizacion}")
  public Iterable<AmortizacionDetalle> findAllByAmortizacionId(@PathVariable Integer id_amortizacion) {
    return repository.findAllByAmortizacionId(id_amortizacion);
  }

  @PostMapping("/amortizaciones_detalles")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid AmortizacionDetalle a) {
    AmortizacionDetalle amortizacion = AmortizacionDetalle.builder()
        .mes(a.getMes())
        .fecha_inicio(a.getFecha_inicio())
        .fecha_fin(a.getFecha_fin())
        .dias(a.getDias())
        .saldo_inicial(a.getSaldo_inicial())
        .capital(a.getCapital())
        .interes(a.getInteres())
        .iva(a.getIva())
        .cuota(a.getCuota())
        .saldo_final(a.getSaldo_final())
        .tasa(a.getTasa())
        .id_amortizacion(a.getId_amortizacion())
        .build();
    repository.save(amortizacion);
    return ResponseController.success("Amortizacion Detalle Agregada Correctamente", amortizacion);
  }

  @PutMapping("/amortizaciones_detalles/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody AmortizacionDetalle a) {
    Optional<AmortizacionDetalle> data = repository.findById(id);
    if (data.isPresent()) {
      AmortizacionDetalle amortizacion = data.get();
      amortizacion.setMes(a.getMes());
      amortizacion.setFecha_inicio(a.getFecha_inicio());
      amortizacion.setFecha_fin(a.getFecha_fin());
      amortizacion.setDias(a.getDias());
      amortizacion.setSaldo_inicial(a.getSaldo_inicial());
      amortizacion.setCapital(a.getCapital());
      amortizacion.setInteres(a.getInteres());
      amortizacion.setIva(a.getIva());
      amortizacion.setCuota(a.getCuota());
      amortizacion.setSaldo_final(a.getSaldo_final());
      amortizacion.setTasa(a.getTasa());
      amortizacion.setId_amortizacion(a.getId_amortizacion());
      repository.save(amortizacion);
      return ResponseController.success("Amortizacion Detalle Actualizada Correctamente", amortizacion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/amortizaciones_detalles/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<AmortizacionDetalle> amortizacion = repository.findById(id);
    if (amortizacion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Amortizacion Detalle Eliminada Correctamente", amortizacion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
