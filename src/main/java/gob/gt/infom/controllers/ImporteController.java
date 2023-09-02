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

import gob.gt.infom.models.Importe;
import gob.gt.infom.repositories.ImporteRepository;
import jakarta.validation.Valid;

@RestController
public class ImporteController {

  @Autowired
  private ImporteRepository repository;

  @GetMapping("/importes")
  public Iterable<Importe> all() {
    return repository.findAll();
  }

  @GetMapping("/importes/{id}")
  public Optional<Importe> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/importes")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Importe i) {
    Importe importe = Importe.builder()
        .mes(i.getMes())
        .fecha(i.getFecha())
        .observaciones(i.getObservaciones())
        .constitucional(i.getConstitucional())
        .iva_paz(i.getIva_paz())
        .vehiculos(i.getVehiculos())
        .petroleo(i.getPetroleo())
        .total(i.getTotal())
        .build();
    repository.save(importe);
    return ResponseController.success("Importe Agregado Correctamente", importe);
  }

  @PutMapping("/importes/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Importe i) {
    Optional<Importe> data = repository.findById(id);
    if (data.isPresent()) {
      Importe importe = data.get();
      importe.setMes(i.getMes());
      importe.setFecha(i.getFecha());
      importe.setObservaciones(i.getObservaciones());
      importe.setConstitucional(i.getConstitucional());
      importe.setIva_paz(i.getIva_paz());
      importe.setVehiculos(i.getVehiculos());
      importe.setPetroleo(i.getPetroleo());
      importe.setTotal(i.getTotal());
      repository.save(importe);
      return ResponseController.success("Importe Actualizado Correctamente", importe);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/importes/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Importe> importe = repository.findById(id);
    if (importe.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Importe Eliminado Correctamente", importe);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
