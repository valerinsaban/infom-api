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

import gob.gt.infom.models.FacturaDetalle;
import gob.gt.infom.repositories.FacturaDetalleRepository;
import jakarta.validation.Valid;

@RestController
public class FacturaDetalleController {

  @Autowired
  private FacturaDetalleRepository repository;

  @GetMapping("/facturas_detalles")
  public Iterable<FacturaDetalle> all() {
    return repository.findAll();
  }

  @GetMapping("/facturas_detalles/{id}")
  public Optional<FacturaDetalle> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/facturas_detalles")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid FacturaDetalle f) {
    FacturaDetalle factura = FacturaDetalle.builder()
        .cantidad(f.getCantidad())
        .tipo(f.getTipo())
        .descripcion(f.getDescripcion())
        .precio_unitario(f.getPrecio_unitario())
        .descuentos(f.getDescuentos())
        .impuestos(f.getImpuestos())
        .subtotal(f.getSubtotal())
        .id_factura(f.getId_factura())
        .build();
    repository.save(factura);
    return ResponseController.success("FacturaDetalle Agregado Correctamente", factura);
  }

  @PutMapping("/facturas_detalles/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody FacturaDetalle f) {
    Optional<FacturaDetalle> data = repository.findById(id);
    if (data.isPresent()) {
      FacturaDetalle factura = data.get();
      factura.setCantidad(f.getCantidad());
      factura.setTipo(f.getTipo());
      factura.setDescripcion(f.getDescripcion());
      factura.setPrecio_unitario(f.getPrecio_unitario());
      factura.setDescuentos(f.getDescuentos());
      factura.setImpuestos(f.getImpuestos());
      factura.setSubtotal(f.getSubtotal());
      factura.setId_factura(f.getId_factura());
      repository.save(factura);
      return ResponseController.success("FacturaDetalle Actualizado Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/facturas_detalles/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<FacturaDetalle> factura = repository.findById(id);
    if (factura.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("FacturaDetalle Eliminado Correctamente", factura);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
