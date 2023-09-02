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

import gob.gt.infom.models.Garantia;
import gob.gt.infom.repositories.GarantiaRepository;
import jakarta.validation.Valid;

@RestController
public class GarantiaController {

  @Autowired
  private GarantiaRepository repository;

  @GetMapping("/garantias")
  public Iterable<Garantia> all() {
    return repository.findAll();
  }

  @GetMapping("/garantias/{id}")
  public Optional<Garantia> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/garantias")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Garantia g) {
    Garantia garantia = Garantia.builder()
        .codigo(g.getCodigo())
        .nombre(g.getNombre())
        .build();
    repository.save(garantia);
    return ResponseController.success("Garantia Agregado Correctamente", garantia);
  }

  @PutMapping("/garantias/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Garantia g) {
    Optional<Garantia> data = repository.findById(id);
    if (data.isPresent()) {
      Garantia garantia = data.get();
      garantia.setCodigo(g.getCodigo());
      garantia.setNombre(g.getNombre());
      repository.save(garantia);
      return ResponseController.success("Garantia Actualizado Correctamente", garantia);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/garantias/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Garantia> garantia = repository.findById(id);
    if (garantia.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Garantia Eliminado Correctamente", garantia);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
