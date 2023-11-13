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

import gob.gt.infom.models.Proyeccion;
import gob.gt.infom.repositories.ProyeccionRepository;
import jakarta.validation.Valid;

@RestController
public class ProyeccionController {

  @Autowired
  private ProyeccionRepository repository;

  @GetMapping("/proyecciones")
  public Iterable<Proyeccion> all() {
    return repository.findAll();
  }

  @GetMapping("/proyecciones/{id}")
  public Optional<Proyeccion> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/proyecciones/prestamo/{id_prestamo}")
  public Iterable<Proyeccion> findAllById_prestamo(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @GetMapping("/proyecciones/prestamo/{id_prestamo}/{mes}")
  public Optional<Proyeccion> findByPrestamoIdAndMes(
      @PathVariable Integer id_prestamo,
      @PathVariable String mes) {
    return repository.findByPrestamoIdAndMes(id_prestamo, mes);
  }

  @PostMapping("/proyecciones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Proyeccion a) {
    Proyeccion proyeccion = Proyeccion.builder()
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
        .disponible(a.getDisponible())
        .id_prestamo(a.getId_prestamo())
        .build();
    repository.save(proyeccion);
    return ResponseController.success("Proyeccion Agregado Correctamente", proyeccion);
  }

  @PutMapping("/proyecciones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Proyeccion a) {
    Optional<Proyeccion> data = repository.findById(id);
    if (data.isPresent()) {
      Proyeccion proyeccion = data.get();
      proyeccion.setMes(a.getMes());
      proyeccion.setFecha_inicio(a.getFecha_inicio());
      proyeccion.setFecha_fin(a.getFecha_fin());
      proyeccion.setDias(a.getDias());
      proyeccion.setSaldo_inicial(a.getSaldo_inicial());
      proyeccion.setCapital(a.getCapital());
      proyeccion.setInteres(a.getInteres());
      proyeccion.setIva(a.getIva());
      proyeccion.setCuota(a.getCuota());
      proyeccion.setSaldo_final(a.getSaldo_final());
      proyeccion.setDisponible(a.getDisponible());
      proyeccion.setId_prestamo(a.getId_prestamo());
      repository.save(proyeccion);
      return ResponseController.success("Proyeccion Actualizado Correctamente", proyeccion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/proyecciones/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Proyeccion> proyeccion = repository.findById(id);
    if (proyeccion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Proyeccion Eliminado Correctamente", proyeccion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
