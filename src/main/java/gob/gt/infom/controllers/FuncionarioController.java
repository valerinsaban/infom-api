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

import gob.gt.infom.models.Funcionario;
import gob.gt.infom.repositories.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController
public class FuncionarioController {

  @Autowired
  private FuncionarioRepository repository;

  @GetMapping("/funcionarios")
  public Iterable<Funcionario> all() {
    return repository.findAll();
  }

  @GetMapping("/funcionarios/municipalidad/{id_municipalidad}")
  public Iterable<Funcionario> findAllByMunicipalidadId(@PathVariable Integer id_municipalidad) {
    return repository.findAllByMunicipalidadId(id_municipalidad);
  }

  @GetMapping("/funcionarios/{id}")
  public Optional<Funcionario> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/funcionarios")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Funcionario p) {
    Funcionario funcionario = Funcionario.builder()
        .nombre(p.getNombre())
        .apellido(p.getApellido())
        .fecha_nacimiento(p.getFecha_nacimiento())
        .dpi(p.getDpi())
        .carnet(p.getCarnet())
        .fecha_carnet(p.getFecha_carnet())
        .acta_toma_posecion(p.getActa_toma_posecion())
        .fecha_acta_toma_posecion(p.getFecha_acta_toma_posecion())
        .estado(p.getEstado())
        .imagen_carnet(p.getImagen_carnet())
        .imagen_acta_toma_posecion(p.getImagen_acta_toma_posecion())
        .imagen_dpi(p.getImagen_dpi())
        .imagen_firma(p.getImagen_firma())
        .imagen_sello(p.getImagen_sello())
        .id_municipalidad(p.getId_municipalidad())
        .id_puesto(p.getId_puesto())
        .id_profesion(p.getId_profesion())
        .id_estado_civil(p.getId_estado_civil())
        .build();
    repository.save(funcionario);
    return ResponseController.success("Funcionario Agregado Correctamente", funcionario);
  }

  @PutMapping("/funcionarios/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Funcionario p) {
    Optional<Funcionario> data = repository.findById(id);
    if (data.isPresent()) {
      Funcionario funcionario = data.get();
      funcionario.setNombre(p.getNombre());
      funcionario.setApellido(p.getApellido());
      funcionario.setFecha_nacimiento(p.getFecha_nacimiento());
      funcionario.setDpi(p.getDpi());
      funcionario.setCarnet(p.getCarnet());
      funcionario.setFecha_carnet(p.getFecha_carnet());
      funcionario.setActa_toma_posecion(p.getActa_toma_posecion());
      funcionario.setFecha_acta_toma_posecion(p.getFecha_acta_toma_posecion());
      funcionario.setEstado(p.getEstado());
      funcionario.setImagen_carnet(p.getImagen_carnet());
      funcionario.setImagen_acta_toma_posecion(p.getImagen_acta_toma_posecion());
      funcionario.setDpi(p.getDpi());
      funcionario.setImagen_firma(p.getImagen_firma());
      funcionario.setImagen_sello(p.getImagen_sello());
      funcionario.setId_municipalidad(p.getId_municipalidad());
      funcionario.setId_puesto(p.getId_puesto());
      funcionario.setId_profesion(p.getId_profesion());
      funcionario.setId_estado_civil(p.getId_estado_civil());
      repository.save(funcionario);
      return ResponseController.success("Funcionario Actualizado Correctamente", funcionario);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/funcionarios/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Funcionario> funcionario = repository.findById(id);
    if (funcionario.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Funcionario Eliminado Correctamente", funcionario);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
