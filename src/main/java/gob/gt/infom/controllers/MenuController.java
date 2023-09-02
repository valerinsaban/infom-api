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

import gob.gt.infom.models.Menu;
import gob.gt.infom.repositories.MenuRepository;
import jakarta.validation.Valid;

@RestController
public class MenuController {

  @Autowired
  private MenuRepository repository;

  @GetMapping("/menus")
  public Iterable<Menu> all() {
    return repository.findAllByOrderByOrdenAsc();
  }

  @GetMapping("/menus/{id}")
  public Optional<Menu> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/menus")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Menu r) {
    Menu menu = Menu.builder()
        .nombre(r.getNombre())
        .build();
    repository.save(menu);
    return ResponseController.success("Menu Agregado Correctamente", menu);
  }

  @PutMapping("/menus/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Menu r) {
    Optional<Menu> data = repository.findById(id);
    if (data.isPresent()) {
      Menu menu = data.get();
      menu.setNombre(r.getNombre());
      repository.save(menu);
      return ResponseController.success("Menu Actualizado Correctamente", menu);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/menus/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Menu> menu = repository.findById(id);
    if (menu.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Menu Eliminado Correctamente", menu);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
