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

import gob.gt.infom.models.Region;
import gob.gt.infom.repositories.RegionRepository;
import jakarta.validation.Valid;

@RestController
public class RegionController {

  @Autowired
  private RegionRepository repository;

  @GetMapping("/regiones")
  public Iterable<Region> all() {
    return repository.findAll();
  }

  @GetMapping("/regiones/{id}")
  public Optional<Region> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/regiones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Region d) {
    Region region = Region.builder()
        .codigo(d.getCodigo())
        .descripcion(d.getDescripcion())
        .build();
    repository.save(region);
    return ResponseController.success("Region Agregado Correctamente", region);
  }

  @PutMapping("/regiones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Region d) {
    Optional<Region> data = repository.findById(id);
    if (data.isPresent()) {
      Region region = data.get();
      region.setCodigo(d.getCodigo());
      region.setDescripcion(d.getDescripcion());
      repository.save(region);
      return ResponseController.success("Region Actualizado Correctamente", region);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/regiones/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Region> region = repository.findById(id);
    if (region.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Region Eliminado Correctamente", region);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
