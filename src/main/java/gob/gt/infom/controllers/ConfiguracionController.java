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

import gob.gt.infom.models.Configuracion;
import gob.gt.infom.repositories.ConfiguracionRepository;
import jakarta.validation.Valid;

@RestController
public class ConfiguracionController {

  @Autowired
  private ConfiguracionRepository repository;

  @GetMapping("/configuraciones")
  public Iterable<Configuracion> all() {
    return repository.findAll();
  }

  @GetMapping("/configuraciones/{id}")
  public Optional<Configuracion> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/configuraciones")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Configuracion c) {
    Configuracion configuracion = Configuracion.builder()
        .logo(c.getLogo())
        .portada(c.getPortada())
        .nombre(c.getNombre())
        .correo(c.getCorreo())
        .telefono(c.getTelefono())
        .direccion(c.getDireccion())
        .sitio_web(c.getSitio_web())
        .porcentaje_interes(c.getPorcentaje_interes())
        .porcentaje_iva(c.getPorcentaje_iva())
        .periodo_fin(c.getPeriodo_fin())
        .build();
    repository.save(configuracion);
    return ResponseController.success("Configuracion Agregado Correctamente", configuracion);
  }

  @PutMapping("/configuraciones/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Configuracion c) {
    Optional<Configuracion> data = repository.findById(id);
    if (data.isPresent()) {
      Configuracion configuracion = data.get();
      configuracion.setLogo(c.getLogo());
      configuracion.setPortada(c.getPortada());
      configuracion.setNombre(c.getNombre());
      configuracion.setCorreo(c.getCorreo());
      configuracion.setTelefono(c.getTelefono());
      configuracion.setDireccion(c.getDireccion());
      configuracion.setSitio_web(c.getSitio_web());
      configuracion.setPorcentaje_interes(c.getPorcentaje_interes());
      configuracion.setPorcentaje_iva(c.getPorcentaje_iva());
      configuracion.setPeriodo_fin(c.getPeriodo_fin());
      repository.save(configuracion);
      return ResponseController.success("Configuracion Actualizado Correctamente", configuracion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/configuraciones/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Configuracion> configuracion = repository.findById(id);
    if (configuracion.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Configuracion Eliminado Correctamente", configuracion);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
