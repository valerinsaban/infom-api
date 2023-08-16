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

import gob.gt.infom.models.Banco;
import gob.gt.infom.repositories.BancoRepository;
import jakarta.validation.Valid;

@RestController
public class BancoController {

  @Autowired
  private BancoRepository repository;

  @GetMapping("/bancos")
  public Iterable<Banco> all() {
    return repository.findAll();
  }

  @GetMapping("/bancos/{id}")
  public Optional<Banco> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/bancos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Banco p) {
    Banco banco = Banco.builder()
        .codigo(p.getCodigo())
        .nombre(p.getNombre())
        .siglas(p.getSiglas())
        .build();
    repository.save(banco);
    return ResponseController.success("Banco Agregado Correctamente", banco);
  }

  @PutMapping("/bancos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Banco p) {
    Optional<Banco> data = repository.findById(id);
    if (data.isPresent()) {
      Banco banco = data.get();
      banco.setCodigo(p.getCodigo());
      banco.setNombre(p.getNombre());
      banco.setSiglas(p.getSiglas());
      repository.save(banco);
      return ResponseController.success("Banco Actualizado Correctamente", banco);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/bancos/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Banco> banco = repository.findById(id);
    if (banco.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Banco Eliminado Correctamente", banco);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
