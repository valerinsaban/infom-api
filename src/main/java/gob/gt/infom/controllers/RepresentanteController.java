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
  public ResponseEntity<Object> create(@RequestBody @Valid Representante p) {
    Representante representante = Representante.builder()
        .nombre(p.getNombre())
        .apellido(p.getApellido())
        .fecha_nacimiento(p.getFecha_nacimiento())
        .dpi(p.getDpi())
        .resolucion(p.getResolucion())
        .fecha_resolucion(p.getFecha_resolucion())
        .acuerdo(p.getAcuerdo())
        .fecha_acuerdo(p.getFecha_acuerdo())
        .jd_resuelve(p.getJd_resuelve())
        .fecha_jd_resuelve(p.getFecha_jd_resuelve())
        .direccion(p.getDireccion())
        .autorizacion(p.getAutorizacion())
        .acta_toma_posecion(p.getActa_toma_posecion())
        .fecha_acta_toma_posecion(p.getFecha_acta_toma_posecion())
        .estado(p.getEstado())
        // .imagen_carnet(p.getImagen_carnet())
        // .imagen_acta_toma_posecion(p.getImagen_acta_toma_posecion())
        // .imagen_fotografia(p.getImagen_fotografia())
        // .imagen_firma(p.getImagen_firma())
        .id_regional(p.getId_regional())
        .id_puesto(p.getId_puesto())
        .id_profesion(p.getId_profesion())
        .id_estado_civil(p.getId_estado_civil())
        .build();
    repository.save(representante);
    return ResponseController.success("Representante Agregado Correctamente", representante);
  }

  @PutMapping("/representantes/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Representante p) {
    Optional<Representante> data = repository.findById(id);
    if (data.isPresent()) {
      Representante representante = data.get();
      representante.setNombre(p.getNombre());
      representante.setApellido(p.getApellido());
      representante.setFecha_nacimiento(p.getFecha_nacimiento());
      representante.setDpi(p.getDpi());
      representante.setResolucion(p.getResolucion());
      representante.setFecha_resolucion(p.getFecha_resolucion());
      representante.setAcuerdo(p.getAcuerdo());
      representante.setFecha_acuerdo(p.getFecha_acuerdo());
      representante.setJd_resuelve(p.getJd_resuelve());
      representante.setFecha_jd_resuelve(p.getFecha_jd_resuelve());
      representante.setDireccion(p.getDireccion());
      representante.setAutorizacion(p.getAutorizacion());
      representante.setFecha_acta_toma_posecion(p.getFecha_acta_toma_posecion());
      representante.setEstado(p.getEstado());
      // representante.setImagen_carnet(p.getImagen_carnet());
      // representante.setImagen_acta_toma_posecion(p.getImagen_acta_toma_posecion());
      // representante.setImagen_fotografia(p.getImagen_fotografia());
      // representante.setImagen_firma(p.getImagen_firma());
      representante.setId_regional(p.getId_regional());
      representante.setId_puesto(p.getId_puesto());
      representante.setId_profesion(p.getId_profesion());
      representante.setId_estado_civil(p.getId_estado_civil());
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
