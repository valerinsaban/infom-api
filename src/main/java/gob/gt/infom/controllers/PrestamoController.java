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

import gob.gt.infom.models.Prestamo;
import gob.gt.infom.repositories.PrestamoRepository;
import jakarta.validation.Valid;

@RestController
public class PrestamoController {

  @Autowired
  private PrestamoRepository repository;

  @GetMapping("/prestamos/{fecha_inicio}/{fecha_fin}")
  public List<Prestamo> all(
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/{estado}/{fecha_inicio}/{fecha_fin}")
  public List<Prestamo> allByEstado(
      @PathVariable String estado,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByEstadoAndFechaBetween(estado, fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/count/{estado}/{fecha_inicio}/{fecha_fin}")
  public Optional<Prestamo> countByEstado(
      @PathVariable String estado,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByEstadoAndFechaBetween(estado, fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/municipalidad/{estado}/{id_municipalidad}")
  public List<Prestamo> findAllByEstadoAndMunicipalidadId(@PathVariable String estado, @PathVariable Integer id_municipalidad) {
    return repository.findAllByEstadoAndMunicipalidadId(estado, id_municipalidad);
  }

  @PostMapping("/prestamos/filtros")
  public Iterable<Prestamo> allByFiltros(@RequestBody Prestamo p) {
    return repository.findAllByUsuarioId(p.getId_usuario());
  }

  @GetMapping("/prestamos/{id}")
  public Optional<Prestamo> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/prestamos")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Prestamo p) {
    Prestamo prestamo = Prestamo.builder()
        .no_dictamen(p.getNo_dictamen())
        .no_pagare(p.getNo_pagare())
        .fecha(p.getFecha())
        .fecha_vencimiento(p.getFecha_vencimiento())
        .fecha_amortizacion(p.getFecha_amortizacion())
        .monto(p.getMonto())
        .plazo_meses(p.getPlazo_meses())
        .fecha_acta(p.getFecha_acta())
        .intereses(p.getIntereses())
        .periodo_gracia(p.getPeriodo_gracia())
        .destino_prestamo(p.getDestino_prestamo())
        .acta(p.getActa())
        .punto(p.getPunto())
        .fecha_memorial(p.getFecha_memorial())
        .certficacion(p.getCertficacion())
        .oficioaj(p.getOficioaj())
        .oficioaj2(p.getOficioaj2())
        .estado(p.getEstado())
        .id_tipo_prestamo(p.getId_tipo_prestamo())
        .id_municipalidad(p.getId_municipalidad())
        .id_funcionario(p.getId_funcionario())
        .id_regional(p.getId_regional())
        .id_usuario(p.getId_usuario())
        .build();
    repository.save(prestamo);
    return ResponseController.success("Prestamo Agregado Correctamente", prestamo);
  }

  @PutMapping("/prestamos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Prestamo p) {
    Optional<Prestamo> data = repository.findById(id);
    if (data.isPresent()) {
      Prestamo prestamo = data.get();
      prestamo.setNo_dictamen(p.getNo_dictamen());
      prestamo.setNo_pagare(p.getNo_pagare());
      prestamo.setFecha(p.getFecha());
      prestamo.setFecha_vencimiento(p.getFecha_vencimiento());
      prestamo.setFecha_amortizacion(p.getFecha_amortizacion());
      prestamo.setMonto(p.getMonto());
      prestamo.setPlazo_meses(p.getPlazo_meses());
      prestamo.setFecha_acta(p.getFecha_acta());
      prestamo.setIntereses(p.getIntereses());
      prestamo.setPeriodo_gracia(p.getPeriodo_gracia());
      prestamo.setDestino_prestamo(p.getDestino_prestamo());
      prestamo.setActa(p.getActa());
      prestamo.setPunto(p.getPunto());
      prestamo.setFecha_memorial(p.getFecha_memorial());
      prestamo.setCertficacion(p.getCertficacion());
      prestamo.setOficioaj(p.getOficioaj());
      prestamo.setOficioaj2(p.getOficioaj2());
      prestamo.setEstado(p.getEstado());
      prestamo.setId_tipo_prestamo(p.getId_tipo_prestamo());
      prestamo.setId_municipalidad(p.getId_municipalidad());
      prestamo.setId_funcionario(p.getId_funcionario());
      prestamo.setId_regional(p.getId_regional());
      prestamo.setId_usuario(p.getId_usuario());
      repository.save(prestamo);
      return ResponseController.success("Prestamo Actualizado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/prestamos/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Prestamo> prestamo = repository.findById(id);
    if (prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Prestamo Eliminado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
