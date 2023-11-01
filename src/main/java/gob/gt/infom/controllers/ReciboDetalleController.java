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

import gob.gt.infom.models.ReciboDetalle;
import gob.gt.infom.repositories.ReciboDetalleRepository;
import jakarta.validation.Valid;

@RestController
public class ReciboDetalleController {

  @Autowired
  private ReciboDetalleRepository repository;

  @GetMapping("/recibos_detalles")
  public Iterable<ReciboDetalle> all() {
    return repository.findAll();
  }

  @GetMapping("/recibos_detalles/{id}")
  public Optional<ReciboDetalle> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/recibos_detalles")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid ReciboDetalle f) {
    ReciboDetalle recibo = ReciboDetalle.builder()
        .cantidad(f.getCantidad())
        .tipo(f.getTipo())
        .descripcion(f.getDescripcion())
        .precio_unitario(f.getPrecio_unitario())
        .descuentos(f.getDescuentos())
        .impuestos(f.getImpuestos())
        .subtotal(f.getSubtotal())
        .id_recibo(f.getId_recibo())
        .build();
    repository.save(recibo);
    return ResponseController.success("Recibo Detalle Agregado Correctamente", recibo);
  }

  @PutMapping("/recibos_detalles/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ReciboDetalle f) {
    Optional<ReciboDetalle> data = repository.findById(id);
    if (data.isPresent()) {
      ReciboDetalle recibo = data.get();
      recibo.setCantidad(f.getCantidad());
      recibo.setTipo(f.getTipo());
      recibo.setDescripcion(f.getDescripcion());
      recibo.setPrecio_unitario(f.getPrecio_unitario());
      recibo.setDescuentos(f.getDescuentos());
      recibo.setImpuestos(f.getImpuestos());
      recibo.setSubtotal(f.getSubtotal());
      recibo.setId_recibo(f.getId_recibo());
      repository.save(recibo);
      return ResponseController.success("Recibo Detalle Actualizado Correctamente", recibo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/recibos_detalles/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<ReciboDetalle> recibo = repository.findById(id);
    if (recibo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Recibo Detalle Eliminado Correctamente", recibo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
