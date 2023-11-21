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

  @GetMapping("/recibos/{fecha_inicio}/{fecha_fin}")
  public Iterable<Recibo> all(
    @PathVariable String fecha_inicio,
    @PathVariable String fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/recibos/{id}")
  public Optional<Recibo> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/recibos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Recibo r) {
    Recibo recibo = Recibo.builder()
        .numero(r.getNumero())
        .fecha(r.getFecha())
        .nit(r.getNit())
        .nombre(r.getNombre())
        .monto(r.getMonto())
        .estado(r.getEstado())
        .descripcion(r.getDescripcion())
        .firma(r.getFirma())
        .id_factura(r.getId_factura())
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
      recibo.setDescripcion(f.getDescripcion());
      recibo.setFirma(f.getFirma());
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
