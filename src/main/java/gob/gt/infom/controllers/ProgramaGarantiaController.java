package gob.gt.infom.controllers;

import java.util.List;
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

import gob.gt.infom.models.ProgramaGarantia;
import gob.gt.infom.repositories.ProgramaGarantiaRepository;
import jakarta.validation.Valid;

@RestController
public class ProgramaGarantiaController {

  @Autowired
  private ProgramaGarantiaRepository repository;

  @GetMapping("/programas_garantias/programa/{id_programa}")
  public List<ProgramaGarantia> one(@PathVariable Integer id_programa) {
    return repository.findAllByProgramaId(id_programa);
  }

  @PostMapping("/programas_garantias")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid ProgramaGarantia p) {
    ProgramaGarantia prestamo = ProgramaGarantia.builder()
        .id_garantia(p.getId_garantia())
        .id_programa(p.getId_programa())
        .build();
    repository.save(prestamo);
    return ResponseController.success("Prestamo Garantia Agregado Correctamente", prestamo);
  }

  @PutMapping("/programas_garantias/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProgramaGarantia p) {
    Optional<ProgramaGarantia> data = repository.findById(id);
    if (data.isPresent()) {
      ProgramaGarantia prestamo = data.get();
      prestamo.setId_garantia(p.getId_garantia());
      prestamo.setId_programa(p.getId_programa());
      repository.save(prestamo);
      return ResponseController.success("Prestamo Garantia Actualizado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/programas_garantias/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<ProgramaGarantia> prestamo = repository.findById(id);
    if (prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Prestamo Garantia Eliminado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
