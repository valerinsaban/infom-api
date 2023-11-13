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

import gob.gt.infom.models.TipoServicio;
import gob.gt.infom.repositories.TipoServicioRepository;
import jakarta.validation.Valid;

@RestController
public class TipoServicioController {

  @Autowired
  private TipoServicioRepository repository;

  @GetMapping("/tipos_servicios")
  public Iterable<TipoServicio> all() {
    return repository.findAll();
  }

  @GetMapping("/tipos_servicios/{id}")
  public Optional<TipoServicio> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/tipos_servicios")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid TipoServicio t) {
    TipoServicio tipo_servicio = TipoServicio.builder()
        .nombre(t.getNombre())
        .siglas(t.getSiglas())
        .centro_costo(t.getCentro_costo())
        .producto(t.getProducto())
        .subproducto(t.getSubproducto())
        .build();
    repository.save(tipo_servicio);
    return ResponseController.success("TipoServicio Agregada Correctamente", tipo_servicio);
  }

  @PutMapping("/tipos_servicios/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody TipoServicio t) {
    Optional<TipoServicio> data = repository.findById(id);
    if (data.isPresent()) {
      TipoServicio tipo_servicio = data.get();
      tipo_servicio.setNombre(t.getNombre());
      tipo_servicio.setSiglas(t.getSiglas());
      tipo_servicio.setCentro_costo(t.getCentro_costo());
      tipo_servicio.setProducto(t.getProducto());
      tipo_servicio.setSubproducto(t.getSubproducto());
      repository.save(tipo_servicio);
      return ResponseController.success("TipoServicio Actualizada Correctamente", tipo_servicio);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/tipos_servicios/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<TipoServicio> tipo_servicio = repository.findById(id);
    if (tipo_servicio.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("TipoServicio Eliminada Correctamente", tipo_servicio);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
