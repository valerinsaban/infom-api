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

    @GetMapping("/funcionarios/ultimo/{id_municipalidad}/{estado}")
  public Optional<Funcionario> findByMunicipalidadIdAndEstado(@PathVariable Integer id_municipalidad, @PathVariable String estado) {
    return repository.findByMunicipalidadIdAndEstado(id_municipalidad, estado);
  }

  @GetMapping("/funcionarios/{id}")
  public Optional<Funcionario> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/funcionarios")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Funcionario f) {
    Funcionario funcionario = Funcionario.builder()
        .nombre(f.getNombre())
        .apellido(f.getApellido())
        .fecha_nacimiento(f.getFecha_nacimiento())
        .dpi(f.getDpi())
        .carnet(f.getCarnet())
        .fecha_carnet(f.getFecha_carnet())
        .acuerdo(f.getAcuerdo())
        .fecha_acuerdo(f.getFecha_acuerdo())
        .acta_toma_posecion(f.getActa_toma_posecion())
        .fecha_acta_toma_posecion(f.getFecha_acta_toma_posecion())
        .estado(f.getEstado())
        .imagen_carnet(f.getImagen_carnet())
        .imagen_acta_toma_posecion(f.getImagen_acta_toma_posecion())
        .imagen_dpi(f.getImagen_dpi())
        .imagen_firma(f.getImagen_firma())
        .imagen_sello(f.getImagen_sello())
        .id_municipalidad(f.getId_municipalidad())
        .id_puesto(f.getId_puesto())
        .id_profesion(f.getId_profesion())
        .id_estado_civil(f.getId_estado_civil())
        .build();
    repository.save(funcionario);
    return ResponseController.success("Funcionario Agregado Correctamente", funcionario);
  }

  @PutMapping("/funcionarios/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Funcionario f) {
    Optional<Funcionario> data = repository.findById(id);
    if (data.isPresent()) {
      Funcionario funcionario = data.get();
      funcionario.setNombre(f.getNombre());
      funcionario.setApellido(f.getApellido());
      funcionario.setFecha_nacimiento(f.getFecha_nacimiento());
      funcionario.setDpi(f.getDpi());
      funcionario.setCarnet(f.getCarnet());
      funcionario.setFecha_carnet(f.getFecha_carnet());
      funcionario.setAcuerdo(f.getAcuerdo());
      funcionario.setFecha_acuerdo(f.getFecha_acuerdo());
      funcionario.setActa_toma_posecion(f.getActa_toma_posecion());
      funcionario.setFecha_acta_toma_posecion(f.getFecha_acta_toma_posecion());
      funcionario.setEstado(f.getEstado());
      funcionario.setImagen_carnet(f.getImagen_carnet());
      funcionario.setImagen_acta_toma_posecion(f.getImagen_acta_toma_posecion());
      funcionario.setDpi(f.getDpi());
      funcionario.setImagen_firma(f.getImagen_firma());
      funcionario.setImagen_sello(f.getImagen_sello());
      funcionario.setId_municipalidad(f.getId_municipalidad());
      funcionario.setId_puesto(f.getId_puesto());
      funcionario.setId_profesion(f.getId_profesion());
      funcionario.setId_estado_civil(f.getId_estado_civil());
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
