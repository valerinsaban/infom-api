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

import gob.gt.infom.models.OrdenPago;
import gob.gt.infom.repositories.OrdenPagoRepository;
import jakarta.validation.Valid;

@RestController
public class OrdenPagoController {

  @Autowired
  private OrdenPagoRepository repository;

  @GetMapping("/ordenes_pagos")
  public Iterable<OrdenPago> all() {
    return repository.findAll();
  }

  @GetMapping("/ordenes_pagos/{id}")
  public Optional<OrdenPago> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/ordenes_pagos/count/{fecha_inicio}/{fecha_fin}")
  public Optional<OrdenPago> one(@PathVariable String fecha_inicio, @PathVariable String fecha_fin) {
    return repository.countByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/ordenes_pagos/prestamo/{id_prestamo}")
  public Iterable<OrdenPago> findAllByPrestamoId(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @PostMapping("/ordenes_pagos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid OrdenPago o) {
    OrdenPago orden_pago = OrdenPago.builder()
        .numero(o.getNumero())
        .fecha(o.getFecha())
        .monto(o.getMonto())
        .no_recibo(o.getNo_recibo())
        .no_acta(o.getNo_acta())
        .punto_acta(o.getPunto_acta())
        .fecha_acta(o.getFecha_acta())
        .id_prestamo(o.getId_prestamo())
        .build();
    repository.save(orden_pago);
    return ResponseController.success("OrdenPago Agregada Correctamente", orden_pago);
  }

  @PutMapping("/ordenes_pagos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody OrdenPago o) {
    Optional<OrdenPago> data = repository.findById(id);
    if (data.isPresent()) {
      OrdenPago orden_pago = data.get();
      orden_pago.setNumero(o.getNumero());
      orden_pago.setFecha(o.getFecha());
      orden_pago.setMonto(o.getMonto());
      orden_pago.setNo_recibo(o.getNo_recibo());
      orden_pago.setNo_acta(o.getNo_acta());
      orden_pago.setPunto_acta(o.getPunto_acta());
      orden_pago.setFecha_acta(o.getFecha_acta());
      orden_pago.setId_prestamo(o.getId_prestamo());
      repository.save(orden_pago);
      return ResponseController.success("OrdenPago Actualizada Correctamente", orden_pago);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/ordenes_pagos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<OrdenPago> orden_pago = repository.findById(id);
    if (orden_pago.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("OrdenPago Eliminada Correctamente", orden_pago);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
