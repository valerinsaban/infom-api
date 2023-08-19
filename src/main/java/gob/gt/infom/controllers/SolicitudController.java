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

import gob.gt.infom.models.Solicitud;
import gob.gt.infom.repositories.SolicitudRepository;
import jakarta.validation.Valid;

@RestController
public class SolicitudController {

  @Autowired
  private SolicitudRepository repository;

  @GetMapping("/solicitudes")
  public Iterable<Solicitud> all() {
    return repository.findAll();
  }

  @GetMapping("/solicitudes/{id}")
  public Optional<Solicitud> one(@PathVariable Long id) {
    return repository.findById(id);
  }

  @PostMapping("/solicitudes")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Solicitud p) {
    Solicitud solicitud = Solicitud.builder()
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
        .id_tipo_prestamo(p.getId_tipo_prestamo())
        .id_municipalidad(p.getId_municipalidad())
        .id_funcionario(p.getId_funcionario())
        .id_regional(p.getId_regional())
        .id_usuario(p.getId_usuario())

        .build();
    repository.save(solicitud);
    return ResponseController.success("Solicitud Agregado Correctamente", solicitud);
  }

  @PutMapping("/solicitudes/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Solicitud p) {
    Optional<Solicitud> data = repository.findById(id);
    if (data.isPresent()) {
      Solicitud solicitud = data.get();
      solicitud.setNo_dictamen(p.getNo_dictamen());
      solicitud.setNo_pagare(p.getNo_pagare());
      solicitud.setFecha(p.getFecha());
      solicitud.setFecha_vencimiento(p.getFecha_vencimiento());
      solicitud.setMonto(p.getMonto());
      solicitud.setPlazo_meses(p.getPlazo_meses());
      solicitud.setFecha_acta(p.getFecha_acta());
      solicitud.setDeposito_intereses(p.getDeposito_intereses());
      solicitud.setIntereses(p.getIntereses());
      solicitud.setIntereses_fecha_fin(p.getIntereses_fecha_fin());
      solicitud.setTiempo_gracia(p.getTiempo_gracia());
      solicitud.setDestino_prestamo(p.getDestino_prestamo());
      solicitud.setCobro_intereses(p.getCobro_intereses());
      solicitud.setActa(p.getActa());
      solicitud.setPunto(p.getPunto());
      solicitud.setFecha_memorial(p.getFecha_memorial());
      solicitud.setAutorizacion(p.getAutorizacion());
      solicitud.setCertficacion(p.getCertficacion());
      solicitud.setOficioaj(p.getOficioaj());
      solicitud.setOficioaj2(p.getOficioaj2());
      solicitud.setId_tipo_prestamo(p.getId_tipo_prestamo());
      solicitud.setId_municipalidad(p.getId_municipalidad());
      solicitud.setId_funcionario(p.getId_funcionario());
      solicitud.setId_regional(p.getId_regional());
      solicitud.setId_usuario(p.getId_usuario());
      repository.save(solicitud);
      return ResponseController.success("Solicitud Actualizado Correctamente", solicitud);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/solicitudes/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Solicitud> solicitud = repository.findById(id);
    if (solicitud.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Solicitud Eliminado Correctamente", solicitud);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
