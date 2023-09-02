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

import gob.gt.infom.models.Submenu;
import gob.gt.infom.repositories.SubmenuRepository;
import jakarta.validation.Valid;

@RestController
public class SubmenuController {

  @Autowired
  private SubmenuRepository repository;

  @GetMapping("/submenus")
  public Iterable<Submenu> all() {
    return repository.findAll();
  }

  @GetMapping("/submenus/{id}")
  public Optional<Submenu> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/submenus")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Submenu r) {
    Submenu submenu = Submenu.builder()
        .nombre(r.getNombre())
        .id_menu(r.getId_menu())
        .build();
    repository.save(submenu);
    return ResponseController.success("Submenu Agregado Correctamente", submenu);
  }

  @PutMapping("/submenus/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Submenu r) {
    Optional<Submenu> data = repository.findById(id);
    if (data.isPresent()) {
      Submenu submenu = data.get();
      submenu.setNombre(r.getNombre());
      submenu.setId_menu(r.getId_menu());
      repository.save(submenu);
      return ResponseController.success("Submenu Actualizado Correctamente", submenu);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/submenus/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Submenu> submenu = repository.findById(id);
    if (submenu.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Submenu Eliminado Correctamente", submenu);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
