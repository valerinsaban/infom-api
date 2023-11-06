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

  @GetMapping("/amortizaciones/cobro/{id_cobro}")
  public Iterable<Amortizacion> findAllByCobroId(@PathVariable Integer id_cobro) {
    return repository.findAllByCobroId(id_cobro);
  }

  @GetMapping("/amortizaciones/programa/{id_programa}")
  public Iterable<Amortizacion> findAllByProgramaId(@PathVariable Integer id_programa) {
    return repository.findAllByProgramaId(id_programa);
  }

  @GetMapping("/amortizaciones/programa/mes/{id_programa}/{mes}")
  public Iterable<Amortizacion> findAllByProgramaIdAndMes(@PathVariable Integer id_programa, @PathVariable String mes) {
    return repository.findAllByProgramaIdAndMes(id_programa, mes);
  }

  @GetMapping("/amortizaciones/prestamo/{id_prestamo}")
  public Iterable<Amortizacion> findAllByPrestamoId(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @GetMapping("/amortizaciones/prestamo/mes/{id_prestamo}/{mes}")
  public Iterable<Amortizacion> findAllByPrestamoIdAndMes(@PathVariable Integer id_prestamo, @PathVariable String mes) {
    return repository.findAllByPrestamoIdAndMes(id_prestamo, mes);
  }

  @GetMapping("/amortizaciones/{id}")
  public Optional<Amortizacion> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/amortizaciones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Amortizacion a) {
    Amortizacion amortizacion = Amortizacion.builder()
        .mes(a.getMes())
        .fecha_inicio(a.getFecha_inicio())
        .fecha_fin(a.getFecha_fin())
        .dias(a.getDias())
        .capital(a.getCapital())
        .interes(a.getInteres())
        .iva(a.getIva())
        .cuota(a.getCuota())
        .saldo_inicial(a.getSaldo_inicial())
        .saldo_final(a.getSaldo_final())
        .tasa(a.getTasa())
        .id_cobro(a.getId_cobro())
        .id_prestamo(a.getId_prestamo())
        .id_programa(a.getId_programa())
        .build();
    repository.save(amortizacion);
    return ResponseController.success("Amortizacion Agregada Correctamente", amortizacion);
  }

  @PutMapping("/amortizaciones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Amortizacion a) {
    Optional<Amortizacion> data = repository.findById(id);
    if (data.isPresent()) {
      Amortizacion amortizacion = data.get();
      amortizacion.setMes(a.getMes());
      amortizacion.setFecha_inicio(a.getFecha_inicio());
      amortizacion.setFecha_fin(a.getFecha_fin());
      amortizacion.setDias(a.getDias());
      amortizacion.setCapital(a.getCapital());
      amortizacion.setInteres(a.getInteres());
      amortizacion.setIva(a.getIva());
      amortizacion.setCuota(a.getCuota());
      amortizacion.setSaldo_inicial(a.getSaldo_inicial());
      amortizacion.setSaldo_final(a.getSaldo_final());
      amortizacion.setTasa(a.getTasa());
      amortizacion.setId_cobro(a.getId_cobro());
      amortizacion.setId_prestamo(a.getId_prestamo());
      amortizacion.setId_programa(a.getId_programa());
      repository.save(amortizacion);
      return ResponseController.success("Amortizacion Actualizada Correctamente", amortizacion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/amortizaciones/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Amortizacion> amortizacion = repository.findById(id);
    if (amortizacion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Amortizacion Eliminada Correctamente", amortizacion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
