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

import gob.gt.infom.models.Recibo;
import gob.gt.infom.repositories.ReciboRepository;
import jakarta.validation.Valid;

@RestController
public class ReciboController {

  @Autowired
  private ReciboRepository repository;

  @GetMapping("/recibos")
  public Iterable<Recibo> all() {
    return repository.findAll();
  }

  @GetMapping("/recibos/{id}")
  public Optional<Recibo> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/recibos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Recibo f) {
    Recibo recibo = Recibo.builder()
        .numero(f.getNumero())
        .fecha(f.getFecha())
        .nit(f.getNit())
        .nombre(f.getNombre())
        .monto(f.getMonto())
        .estado(f.getEstado())
        .id_factura(f.getId_factura())
        .build();
    repository.save(recibo);
    return ResponseController.success("Recibo Agregado Correctamente", recibo);
  }

  @PutMapping("/recibos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Recibo f) {
    Optional<Recibo> data = repository.findById(id);
    if (data.isPresent()) {
      Recibo recibo = data.get();
      recibo.setNumero(f.getNumero());
      recibo.setFecha(f.getFecha());
      recibo.setNit(f.getNit());
      recibo.setNombre(f.getNombre());
      recibo.setMonto(f.getMonto());
      recibo.setEstado(f.getEstado());
      recibo.setId_factura(f.getId_factura());
      repository.save(recibo);
      return ResponseController.success("Recibo Actualizado Correctamente", recibo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/recibos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Recibo> recibo = repository.findById(id);
    if (recibo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Recibo Eliminado Correctamente", recibo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
