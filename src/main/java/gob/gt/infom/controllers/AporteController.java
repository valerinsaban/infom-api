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

import gob.gt.infom.models.Aporte;
import gob.gt.infom.repositories.AporteRepository;
import jakarta.validation.Valid;

@RestController
public class AporteController {

  @Autowired
  private AporteRepository repository;

  @GetMapping("/aportes")
  public Iterable<Aporte> all() {
    return repository.findAll();
  }

  @GetMapping("/aportes/{mes_inicio}/{mes_fin}/{codigo_departamento}/{codigo_municipio}")
  public Iterable<Aporte> findAllByMesBetweenAndCodigoDepartamentoAndCodigoMunicipio(@PathVariable String mes_inicio, @PathVariable String mes_fin, @PathVariable String codigo_departamento, @PathVariable String codigo_municipio) {
    return repository.findAllByMesBetweenAndCodigoDepartamentoAndCodigoMunicipio(mes_inicio, mes_fin, codigo_departamento, codigo_municipio);
  }

  @GetMapping("/aportes/mes/{mes}")
  public Iterable<Aporte> findAllByMes(@PathVariable String mes) {
    return repository.findAllByMes(mes);
  }

  @GetMapping("/aportes/municipalidad/{codigo_departamento}/{codigo_municipio}")
  public Optional<Aporte> findTopByCodigoDepartamentoAndCodigoMunicipioOrderByMesDesc(@PathVariable String codigo_departamento, @PathVariable String codigo_municipio) {
    return repository.findTopByCodigoDepartamentoAndCodigoMunicipioOrderByMesDesc(codigo_departamento, codigo_municipio);
  }

  @GetMapping("/aportes/municipalidad/mes/{codigo_departamento}/{codigo_municipio}/{mes}")
  public Optional<Aporte> findByCodigoDepartamentoAndCodigoMunicipioAndMes(@PathVariable String codigo_departamento, @PathVariable String codigo_municipio, @PathVariable String mes) {
    return repository.findByCodigoDepartamentoAndCodigoMunicipioAndMes(codigo_departamento, codigo_municipio, mes);
  }

  @GetMapping("/aportes/{id}")
  public Optional<Aporte> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/aportes")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Aporte a) {
    Aporte aporte = Aporte.builder()
        .mes(a.getMes())
        .constitucional(a.getConstitucional())
        .iva_paz(a.getIva_paz())
        .vehiculos(a.getVehiculos())
        .petroleo(a.getPetroleo())
        .total(a.getTotal())
        .codigoDepartamento(a.getCodigoDepartamento())
        .codigoMunicipio(a.getCodigoMunicipio())
        .id_importe(a.getId_importe())
        .build();
    repository.save(aporte);
    return ResponseController.success("Aporte Agregado Correctamente", aporte);
  }

  @PutMapping("/aportes/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Aporte a) {
    Optional<Aporte> data = repository.findById(id);
    if (data.isPresent()) {
      Aporte aporte = data.get();
      aporte.setMes(a.getMes());
      aporte.setConstitucional(a.getConstitucional());
      aporte.setIva_paz(a.getIva_paz());
      aporte.setVehiculos(a.getVehiculos());
      aporte.setPetroleo(a.getPetroleo());
      aporte.setTotal(a.getTotal());
      aporte.setCodigoDepartamento(a.getCodigoDepartamento());
      aporte.setCodigoMunicipio(a.getCodigoMunicipio());
      aporte.setId_importe(a.getId_importe());
      repository.save(aporte);
      return ResponseController.success("Aporte Actualizado Correctamente", aporte);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/aportes/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Aporte> aporte = repository.findById(id);
    if (aporte.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Aporte Eliminado Correctamente", aporte);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
