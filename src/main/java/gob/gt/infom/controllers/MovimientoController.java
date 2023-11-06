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

import gob.gt.infom.models.Movimiento;
import gob.gt.infom.repositories.MovimientoRepository;
import jakarta.validation.Valid;

@RestController
public class MovimientoController {

  @Autowired
  private MovimientoRepository repository;

  @GetMapping("/movimientos")
  public Iterable<Movimiento> all() {
    return repository.findAll();
  }

  @GetMapping("/movimientos/{id}")
  public Optional<Movimiento> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/movimientos/ultimo/{id_prestamo}")
  public Optional<Movimiento> findTopByPrestamoId(@PathVariable Integer id_prestamo) {
    return repository.findTopByPrestamoId(id_prestamo);
  }

  @GetMapping("/movimientos/orden_pago/{id_orden_pago}")
  public Iterable<Movimiento> findAllByOrdenPagoId(@PathVariable Integer id_orden_pago) {
    return repository.findAllByOrdenPagoId(id_orden_pago);
  }

  @GetMapping("/movimientos/recibo/{id_recibo}")
  public Iterable<Movimiento> findAllByReciboId(@PathVariable Integer id_recibo) {
    return repository.findAllByReciboId(id_recibo);
  }

  @GetMapping("/movimientos/prestamo/{id_prestamo}")
  public Iterable<Movimiento> findAllByPrestamoId(@PathVariable Integer id_prestamo) {
    return repository.findAllByPrestamoId(id_prestamo);
  }

  @PostMapping("/movimientos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Movimiento m) {
    Movimiento movimiento = Movimiento.builder()
        .fecha(m.getFecha())
        .saldo_inicial(m.getSaldo_inicial())
        .cargo(m.getCargo())
        .abono(m.getAbono())
        .saldo_final(m.getSaldo_final())
        .id_prestamo(m.getId_prestamo())
        .id_orden_pago(m.getId_orden_pago())
        .id_recibo(m.getId_recibo())
        .build();
    repository.save(movimiento);
    return ResponseController.success("Movimiento Agregado Correctamente", movimiento);
  }

  @PutMapping("/movimientos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Movimiento m) {
    Optional<Movimiento> data = repository.findById(id);
    if (data.isPresent()) {
      Movimiento movimiento = data.get();
      movimiento.setFecha(m.getFecha());
      movimiento.setSaldo_inicial(m.getSaldo_inicial());
      movimiento.setCargo(m.getCargo());
      movimiento.setAbono(m.getAbono());
      movimiento.setSaldo_final(m.getSaldo_final());
      movimiento.setId_prestamo(m.getId_prestamo());
      movimiento.setId_orden_pago(m.getId_orden_pago());
      movimiento.setId_recibo(m.getId_recibo());
      repository.save(movimiento);
      return ResponseController.success("Movimiento Actualizado Correctamente", movimiento);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/movimientos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Movimiento> movimiento = repository.findById(id);
    if (movimiento.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Movimiento Eliminado Correctamente", movimiento);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
