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

import gob.gt.infom.models.Municipalidad;
import gob.gt.infom.repositories.MunicipalidadRepository;
import jakarta.validation.Valid;

@RestController
public class MunicipalidadController {

  @Autowired
  private MunicipalidadRepository repository;

  @GetMapping("/municipalidades")
  public Iterable<Municipalidad> all() {
    return repository.findAll();
  }

  @GetMapping("/municipalidades/{codigo_departamento}/{codigo_municipio}")
  public Optional<Municipalidad> findOneByDepartamentoCodigoAndMunicipioCodigo(@PathVariable String codigo_departamento, @PathVariable String codigo_municipio) {
    return repository.findOneByDepartamentoCodigoAndMunicipioCodigo(codigo_departamento, codigo_municipio);
  }

  @GetMapping("/municipalidades/{codigo_departamento}")
  public Iterable<Municipalidad> findAllByDepartamentoCodigo(@PathVariable String codigo_departamento) {
    return repository.findAllByDepartamentoCodigo(codigo_departamento);
  }

  @PostMapping("/municipalidades")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Municipalidad m) {
    Municipalidad municipalidad = Municipalidad.builder()
        .direccion(m.getDireccion())
        .correo(m.getCorreo())
        .telefono(m.getTelefono())
        .no_cuenta(m.getNo_cuenta())
        .id_departamento(m.getId_departamento())
        .id_municipio(m.getId_municipio())
        .build();
    repository.save(municipalidad);
    return ResponseController.success("Municipalidad Agregado Correctamente", municipalidad);
  }

  @PutMapping("/municipalidades/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Municipalidad m) {
    Optional<Municipalidad> data = repository.findById(id);
    if (data.isPresent()) {
      Municipalidad municipalidad = data.get();
      municipalidad.setDireccion(m.getDireccion());
      municipalidad.setCorreo(m.getCorreo());
      municipalidad.setTelefono(m.getTelefono());
      municipalidad.setNo_cuenta(m.getNo_cuenta());
      municipalidad.setId_departamento(m.getId_departamento());
      municipalidad.setId_municipio(m.getId_municipio());
      repository.save(municipalidad);
      return ResponseController.success("Municipalidad Actualizado Correctamente", municipalidad);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/municipalidades/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Municipalidad> municipalidad = repository.findById(id);
    if (municipalidad.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Municipalidad Eliminado Correctamente", municipalidad);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
