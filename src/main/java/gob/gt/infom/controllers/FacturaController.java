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

  @GetMapping("/facturas/{fecha_inicio}/{fecha_fin}")
  public Iterable<Factura> all(
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
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
        .nombre(f.getNombre())
        .monto(f.getMonto())
        .subtotal(f.getSubtotal())
        .impuestos(f.getImpuestos())
        .estado(f.getEstado())
        .descripcion(f.getDescripcion())
        .autorizacion(f.getAutorizacion())
        .serie_fel(f.getSerie_fel())
        .numero_fel(f.getNumero_fel())
        .uuid(f.getUuid())
        .id_amortizacion(f.getId_amortizacion())
        .build();
    repository.save(factura);
    return ResponseController.success("Factura Agregada Correctamente", factura);
  }

  @PutMapping("/facturas/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Factura f) {
    Optional<Factura> data = repository.findById(id);
    if (data.isPresent()) {
      Factura factura = data.get();
      factura.setNumero(f.getNumero());
      factura.setFecha(f.getFecha());
      factura.setNit(f.getNit());
      factura.setNombre(f.getNombre());
      factura.setMonto(f.getMonto());
      factura.setSubtotal(f.getSubtotal());
      factura.setImpuestos(f.getImpuestos());
      factura.setEstado(f.getEstado());
      factura.setDescripcion(f.getDescripcion());
      factura.setAutorizacion(f.getAutorizacion());
      factura.setSerie_fel(f.getSerie_fel());
      factura.setNumero_fel(f.getNumero_fel());
      factura.setUuid(f.getUuid());
      factura.setId_amortizacion(f.getId_amortizacion());
      repository.save(factura);
      return ResponseController.success("Factura Actualizada Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/facturas/anular/{id}")
  public ResponseEntity<?> anular(@PathVariable("id") Integer id, @RequestBody Factura f) {
    Optional<Factura> data = repository.findById(id);
    if (data.isPresent()) {
      Factura factura = data.get();
      factura.setEstado(f.getEstado());
      repository.save(factura);
      return ResponseController.success("Factura Anulada Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/facturas/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Factura> factura = repository.findById(id);
    if (factura.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Factura Eliminada Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
