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

import gob.gt.infom.models.Cobro;
import gob.gt.infom.repositories.CobroRepository;
import jakarta.validation.Valid;

@RestController
public class CobroController {

  @Autowired
  private CobroRepository repository;

  @GetMapping("/cobros")
  public Iterable<Cobro> all() {
    return repository.findAll();
  }

  @GetMapping("/cobros/ultimo")
  public Optional<Cobro> findTopByOrderByMesDesc() {
    return repository.findTopByOrderByMesDesc();
  }

  @GetMapping("/cobros/mes/{mes}")
  public Optional<Cobro> findByMes(@PathVariable String mes) {
    return repository.findByMes(mes);
  }

  @GetMapping("/cobros/{id}")
  public Optional<Cobro> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/cobros")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Cobro c) {
    Cobro cobro = Cobro.builder()
        .codigo(c.getCodigo())
        .fecha(c.getFecha())
        .mes(c.getMes())
        .build();
    repository.save(cobro);
    return ResponseController.success("Cobro Agregado Correctamente", cobro);
  }

  @PutMapping("/cobros/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Cobro c) {
    Optional<Cobro> data = repository.findById(id);
    if (data.isPresent()) {
      Cobro cobro = data.get();
      cobro.setCodigo(c.getCodigo());
      cobro.setFecha(c.getFecha());
      cobro.setMes(c.getMes());
      repository.save(cobro);
      return ResponseController.success("Cobro Actualizado Correctamente", cobro);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/cobros/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Cobro> cobro = repository.findById(id);
    if (cobro.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Cobro Eliminado Correctamente", cobro);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
