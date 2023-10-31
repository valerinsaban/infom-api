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

import gob.gt.infom.models.Factura;
import gob.gt.infom.repositories.FacturaRepository;
import jakarta.validation.Valid;

@RestController
public class FacturaController {

  @Autowired
  private FacturaRepository repository;

  @GetMapping("/facturas")
  public Iterable<Factura> all() {
    return repository.findAll();
  }

  @GetMapping("/facturas/{id}")
  public Optional<Factura> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/facturas")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Factura f) {
    Factura factura = Factura.builder()
        .numero(f.getNumero())
        .fecha(f.getFecha())
        .nit(f.getNit())
        .monto(f.getMonto())
        .estado(f.getEstado())
        .autorizacion(f.getAutorizacion())
        .serie_fel(f.getSerie_fel())
        .numero_fel(f.getNumero_fel())
        .build();
    repository.save(factura);
    return ResponseController.success("Factura Agregado Correctamente", factura);
  }

  @PutMapping("/facturas/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Factura f) {
    Optional<Factura> data = repository.findById(id);
    if (data.isPresent()) {
      Factura factura = data.get();
      factura.setNumero(f.getNumero());
      factura.setFecha(f.getFecha());
      factura.setNit(f.getNit());
      factura.setMonto(f.getMonto());
      factura.setEstado(f.getEstado());
      factura.setAutorizacion(f.getAutorizacion());
      factura.setSerie_fel(f.getSerie_fel());
      factura.setNumero_fel(f.getNumero_fel());
      repository.save(factura);
      return ResponseController.success("Factura Actualizado Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/facturas/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Factura> factura = repository.findById(id);
    if (factura.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Factura Eliminado Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
