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

import gob.gt.infom.models.Banco;
import gob.gt.infom.repositories.BancoRepository;
import jakarta.validation.Valid;

@RestController
public class BancoController {

  @Autowired
  private BancoRepository repository;

  @GetMapping("/bancos")
  public Iterable<Banco> all() {
    return repository.findAll();
  }

  @GetMapping("/bancos/{id}")
  public Optional<Banco> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/bancos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Banco b) {
    Banco banco = Banco.builder()
        .nombre(b.getNombre())
        .siglas(b.getSiglas())
        .r_nombre(b.getR_nombre())
        .r_apellido(b.getR_apellido())
        .fecha_nacimiento(b.getFecha_nacimiento())
        .dpi(b.getDpi())
        .notario_autoriza(b.getNotario_autoriza())
        .acta_notarial(b.getActa_notarial())
        .fecha_acta_notarial(b.getFecha_acta_notarial())
        .libro(b.getLibro())
        .folio(b.getFolio())
        .id_profesion(b.getId_profesion())
        .id_estado_civil(b.getId_estado_civil())
        .build();
    repository.save(banco);
    return ResponseController.success("Banco Agregado Correctamente", banco);
  }

  @PutMapping("/bancos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Banco b) {
    Optional<Banco> data = repository.findById(id);
    if (data.isPresent()) {
      Banco banco = data.get();
      banco.setNombre(b.getNombre());
      banco.setSiglas(b.getSiglas());
      banco.setR_nombre(b.getR_nombre());
      banco.setR_apellido(b.getR_apellido());
      banco.setFecha_nacimiento(b.getFecha_nacimiento());
      banco.setDpi(b.getDpi());
      banco.setNotario_autoriza(b.getNotario_autoriza());
      banco.setActa_notarial(b.getActa_notarial());
      banco.setFecha_acta_notarial(b.getFecha_acta_notarial());
      banco.setLibro(b.getLibro());
      banco.setFolio(b.getFolio());
      banco.setId_profesion(b.getId_profesion());
      banco.setId_estado_civil(b.getId_estado_civil());
      repository.save(banco);
      return ResponseController.success("Banco Actualizado Correctamente", banco);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/bancos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Banco> banco = repository.findById(id);
    if (banco.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Banco Eliminado Correctamente", banco);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
