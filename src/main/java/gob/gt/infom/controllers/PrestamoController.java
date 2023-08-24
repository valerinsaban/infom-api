package gob.gt.infom.controllers;

import java.util.Date;
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
  public List<Prestamo> all(@PathVariable Date fecha_inicio, Date fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/{id}")
  public Optional<Prestamo> one(@PathVariable Long id) {
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
        .monto(p.getMonto())
        .plazo_meses(p.getPlazo_meses())
        .fecha_acta(p.getFecha_acta())
        .deposito_intereses(p.getDeposito_intereses())
        .intereses(p.getIntereses())
        .intereses_fecha_fin(p.getIntereses_fecha_fin())
        .tiempo_gracia(p.getTiempo_gracia())
        .destino_prestamo(p.getDestino_prestamo())
        .cobro_intereses(p.getCobro_intereses())
        .acta(p.getActa())
        .punto(p.getPunto())
        .fecha_memorial(p.getFecha_memorial())
        .autorizacion(p.getAutorizacion())
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
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Prestamo p) {
    Optional<Prestamo> data = repository.findById(id);
    if (data.isPresent()) {
      Prestamo prestamo = data.get();
      prestamo.setNo_dictamen(p.getNo_dictamen());
      prestamo.setNo_pagare(p.getNo_pagare());
      prestamo.setFecha(p.getFecha());
      prestamo.setFecha_vencimiento(p.getFecha_vencimiento());
      prestamo.setMonto(p.getMonto());
      prestamo.setPlazo_meses(p.getPlazo_meses());
      prestamo.setFecha_acta(p.getFecha_acta());
      prestamo.setDeposito_intereses(p.getDeposito_intereses());
      prestamo.setIntereses(p.getIntereses());
      prestamo.setIntereses_fecha_fin(p.getIntereses_fecha_fin());
      prestamo.setTiempo_gracia(p.getTiempo_gracia());
      prestamo.setDestino_prestamo(p.getDestino_prestamo());
      prestamo.setCobro_intereses(p.getCobro_intereses());
      prestamo.setActa(p.getActa());
      prestamo.setPunto(p.getPunto());
      prestamo.setFecha_memorial(p.getFecha_memorial());
      prestamo.setAutorizacion(p.getAutorizacion());
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
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Prestamo> prestamo = repository.findById(id);
    if (prestamo.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Prestamo Eliminado Correctamente", prestamo);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
