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

import gob.gt.infom.models.Bitacora;
import gob.gt.infom.repositories.BitacoraRepository;
import jakarta.validation.Valid;

@RestController
public class BitacoraController {

  @Autowired
  private BitacoraRepository repository;

  @GetMapping("/bitacoras")
  public Iterable<Bitacora> all() {
    return repository.findAll();
  }

  @GetMapping("/bitacoras/{fecha_inicio}/{fecha_fin}")
  public Iterable<Bitacora> findAllByFechaBetween(@PathVariable String fecha_inicio, @PathVariable String fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/bitacoras/{id}")
  public Optional<Bitacora> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/bitacoras")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Bitacora b) {
    Bitacora bitacora = Bitacora.builder()
        .fecha(b.getFecha())
        .tipo(b.getTipo())
        .accion(b.getAccion())
        .descripcion(b.getDescripcion())
        .body(b.getBody())
        .id_usuario(b.getId_usuario())
        .id_menu(b.getId_menu())
        .id_submenu(b.getId_submenu())
        .build();
    repository.save(bitacora);
    return ResponseController.success("Bitacora Agregado Correctamente", bitacora);
  }

  @PutMapping("/bitacoras/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Bitacora b) {
    Optional<Bitacora> data = repository.findById(id);
    if (data.isPresent()) {
      Bitacora bitacora = data.get();
      bitacora.setFecha(b.getFecha());
      bitacora.setTipo(b.getTipo());
      bitacora.setAccion(b.getAccion());
      bitacora.setDescripcion(b.getDescripcion());
      bitacora.setBody(b.getBody());
      bitacora.setId_usuario(b.getId_usuario());
      bitacora.setId_menu(b.getId_menu());
      bitacora.setId_submenu(b.getId_submenu());
      repository.save(bitacora);
      return ResponseController.success("Bitacora Actualizado Correctamente", bitacora);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/bitacoras/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Bitacora> bitacora = repository.findById(id);
    if (bitacora.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Bitacora Eliminado Correctamente", bitacora);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
