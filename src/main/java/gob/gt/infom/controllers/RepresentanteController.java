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

import gob.gt.infom.models.Representante;
import gob.gt.infom.repositories.RepresentanteRepository;
import jakarta.validation.Valid;

@RestController
public class RepresentanteController {

  @Autowired
  private RepresentanteRepository repository;

  @GetMapping("/representantes")
  public Iterable<Representante> all() {
    return repository.findAll();
  }

  @GetMapping("/representantes/{id}")
  public Optional<Representante> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/representantes")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Representante r) {
    Representante representante = Representante.builder()
        .nombre(r.getNombre())
        .apellido(r.getApellido())
        .fecha_nacimiento(r.getFecha_nacimiento())
        .dpi(r.getDpi())
        .resolucion(r.getResolucion())
        .fecha_resolucion(r.getFecha_resolucion())
        .acuerdo(r.getAcuerdo())
        .fecha_acuerdo(r.getFecha_acuerdo())
        .jd_resuelve(r.getJd_resuelve())
        .fecha_jd_resuelve(r.getFecha_jd_resuelve())
        .direccion(r.getDireccion())
        .autorizacion(r.getAutorizacion())
        .acta_toma_posecion(r.getActa_toma_posecion())
        .fecha_acta_toma_posecion(r.getFecha_acta_toma_posecion())
        .estado(r.getEstado())
        .imagen_carnet(r.getImagen_carnet())
        .imagen_acta_toma_posecion(r.getImagen_acta_toma_posecion())
        .imagen_dpi(r.getImagen_dpi())
        .imagen_firma(r.getImagen_firma())
        .imagen_sello(r.getImagen_sello())
        .id_regional(r.getId_regional())
        .id_puesto(r.getId_puesto())
        .id_profesion(r.getId_profesion())
        .id_estado_civil(r.getId_estado_civil())
        .build();
    repository.save(representante);
    return ResponseController.success("Representante Agregado Correctamente", representante);
  }

  @PutMapping("/representantes/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Representante r) {
    Optional<Representante> data = repository.findById(id);
    if (data.isPresent()) {
      Representante representante = data.get();
      representante.setNombre(r.getNombre());
      representante.setApellido(r.getApellido());
      representante.setFecha_nacimiento(r.getFecha_nacimiento());
      representante.setDpi(r.getDpi());
      representante.setResolucion(r.getResolucion());
      representante.setFecha_resolucion(r.getFecha_resolucion());
      representante.setAcuerdo(r.getAcuerdo());
      representante.setFecha_acuerdo(r.getFecha_acuerdo());
      representante.setJd_resuelve(r.getJd_resuelve());
      representante.setFecha_jd_resuelve(r.getFecha_jd_resuelve());
      representante.setDireccion(r.getDireccion());
      representante.setAutorizacion(r.getAutorizacion());
      representante.setFecha_acta_toma_posecion(r.getFecha_acta_toma_posecion());
      representante.setEstado(r.getEstado());
      representante.setImagen_carnet(r.getImagen_carnet());
      representante.setImagen_acta_toma_posecion(r.getImagen_acta_toma_posecion());
      representante.setImagen_dpi(r.getImagen_dpi());
      representante.setImagen_firma(r.getImagen_firma());
      representante.setImagen_sello(r.getImagen_sello());
      representante.setId_regional(r.getId_regional());
      representante.setId_puesto(r.getId_puesto());
      representante.setId_profesion(r.getId_profesion());
      representante.setId_estado_civil(r.getId_estado_civil());
      repository.save(representante);
      return ResponseController.success("Representante Actualizado Correctamente", representante);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/representantes/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Representante> representante = repository.findById(id);
    if (representante.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Representante Eliminado Correctamente", representante);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
