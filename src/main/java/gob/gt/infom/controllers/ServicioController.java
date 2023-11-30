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

import gob.gt.infom.models.Servicio;
import gob.gt.infom.repositories.ServicioRepository;
import jakarta.validation.Valid;

@RestController
public class ServicioController {

  @Autowired
  private ServicioRepository repository;

  @GetMapping("/servicios/estado/{estado}")
  public List<Servicio> allByEstado(
      @PathVariable String estado) {
    return repository.findAllByEstado(estado);
  }

  @GetMapping("/servicios/{fecha_inicio}/{fecha_fin}")
  public List<Servicio> all(
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/servicios/{estado}/{fecha_inicio}/{fecha_fin}")
  public List<Servicio> allByEstadoFecha(
      @PathVariable String estado,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByEstadoAndFechaBetween(estado, fecha_inicio, fecha_fin);
  }

  @GetMapping("/servicios/count/fecha/{fecha_inicio}/{fecha_fin}")
  public Optional<Servicio> countByFecha(
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/servicios/count/{estado}/{fecha_inicio}/{fecha_fin}")
  public Optional<Servicio> countByEstado(
      @PathVariable String estado,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByEstadoAndFechaBetween(estado, fecha_inicio, fecha_fin);
  }

  @GetMapping("/servicios/municipalidad/{estado}/{id_municipalidad}")
  public List<Servicio> findAllByEstadoAndMunicipalidadId(
      @PathVariable String estado,
      @PathVariable Integer id_municipalidad) {
    return repository.findAllByEstadoAndMunicipalidadId(estado, id_municipalidad);
  }

  @GetMapping("/servicios/programa/{estado}/{id_programa}")
  public List<Servicio> findAllByEstadoAndProgramaId(
      @PathVariable String estado,
      @PathVariable Integer id_programa) {
    return repository.findAllByEstadoAndProgramaId(estado, id_programa);
  }

  @GetMapping("/servicios/municipalidad/programa/{estado}/{id_municipalidad}/{id_programa}")
  public List<Servicio> findAllByEstadoAndMunicipalidadIdAndProgramaId(
      @PathVariable String estado,
      @PathVariable Integer id_municipalidad,
      @PathVariable Integer id_programa) {
    return repository.findAllByEstadoAndMunicipalidadIdAndProgramaId(estado, id_municipalidad, id_programa);
  }

  @GetMapping("/servicios/tipo_servicio/{id_tipo_servicio}/programa/{id_programa}/rango/{fecha_inicio}/{fecha_fin}")
  public Optional<Servicio> countByTipoServicioIdAndProgramaIdAndFechaBetween(
      @PathVariable Integer id_tipo_servicio,
      @PathVariable Integer id_programa,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByTipoServicioIdAndProgramaIdAndFechaBetween(id_tipo_servicio, id_programa, fecha_inicio,
        fecha_fin);
  }

  @GetMapping("/servicios/municipalidad/{id_municipalidad}")
  public Optional<Servicio> countByMunicipalidadId(@PathVariable Integer id_municipalidad) {
    return repository.countByMunicipalidadId(id_municipalidad);
  }

  @PostMapping("/servicios/filtros")
  public Iterable<Servicio> allByFiltros(@RequestBody Servicio p) {
    return repository.findAllByUsuarioId(p.getId_usuario());
  }

  @GetMapping("/servicios/{id}")
  public Optional<Servicio> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @PostMapping("/servicios")
  @ResponseBody
  public ResponseEntity<Object> create(@RequestBody @Valid Servicio s) {
    Servicio servicio = Servicio.builder()
        .no_dictamen(s.getNo_dictamen())
        .no_convenio(s.getNo_convenio())
        .no_servicio(s.getNo_servicio())
        .fecha(s.getFecha())
        .fecha_amortizacion(s.getFecha_amortizacion())
        .fecha_vencimiento(s.getFecha_vencimiento())
        .monto(s.getMonto())
        .plazo_meses(s.getPlazo_meses())
        .fecha_acta(s.getFecha_acta())
        .tasa(s.getTasa())
        .periodo_gracia(s.getPeriodo_gracia())
        .destino(s.getDestino())
        .no_destinos(s.getNo_destinos())
        .acta(s.getActa())
        .punto(s.getPunto())
        .fecha_memorial(s.getFecha_memorial())
        .certificacion(s.getCertificacion())
        .no_oficio_aj(s.getNo_oficio_aj())
        .fecha_oficio_aj(s.getFecha_oficio_aj())
        .no_oficio_ger(s.getNo_oficio_ger())
        .fecha_oficio_ger(s.getFecha_oficio_ger())
        .motivo_anulacion(s.getMotivo_anulacion())
        .estado(s.getEstado())
        .id_tipo_servicio(s.getId_tipo_servicio())
        .id_tipo_prestamo(s.getId_tipo_prestamo())
        .id_programa(s.getId_programa())
        .id_municipalidad(s.getId_municipalidad())
        .id_resolucion(s.getId_resolucion())
        .id_funcionario(s.getId_funcionario())
        .id_regional(s.getId_regional())
        .id_usuario(s.getId_usuario())
        .build();
    repository.save(servicio);
    return ResponseController.success("Servicio Creado Correctamente", servicio);
  }

  @PutMapping("/servicios/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Servicio s) {
    Optional<Servicio> data = repository.findById(id);
    if (data.isPresent()) {
      Servicio servicio = data.get();
      servicio.setNo_dictamen(s.getNo_dictamen());
      servicio.setNo_convenio(s.getNo_convenio());
      servicio.setNo_servicio(s.getNo_servicio());
      servicio.setFecha(s.getFecha());
      servicio.setFecha_amortizacion(s.getFecha_amortizacion());
      servicio.setFecha_vencimiento(s.getFecha_vencimiento());
      servicio.setMonto(s.getMonto());
      servicio.setPlazo_meses(s.getPlazo_meses());
      servicio.setFecha_acta(s.getFecha_acta());
      servicio.setTasa(s.getTasa());
      servicio.setPeriodo_gracia(s.getPeriodo_gracia());
      servicio.setDestino(s.getDestino());
      servicio.setNo_destinos(s.getNo_destinos());
      servicio.setActa(s.getActa());
      servicio.setPunto(s.getPunto());
      servicio.setFecha_memorial(s.getFecha_memorial());
      servicio.setCertificacion(s.getCertificacion());
      servicio.setNo_oficio_aj(s.getNo_oficio_aj());
      servicio.setFecha_oficio_aj(s.getFecha_oficio_aj());
      servicio.setNo_oficio_ger(s.getNo_oficio_ger());
      servicio.setFecha_oficio_ger(s.getFecha_oficio_ger());
      servicio.setMotivo_anulacion(s.getMotivo_anulacion());
      servicio.setEstado(s.getEstado());
      servicio.setId_tipo_servicio(s.getId_tipo_servicio());
      servicio.setId_tipo_prestamo(s.getId_tipo_prestamo());
      servicio.setId_programa(s.getId_programa());
      servicio.setId_resolucion(s.getId_resolucion());
      servicio.setId_municipalidad(s.getId_municipalidad());
      servicio.setId_funcionario(s.getId_funcionario());
      servicio.setId_regional(s.getId_regional());
      servicio.setId_usuario(s.getId_usuario());
      repository.save(servicio);
      return ResponseController.success("Servicio Actualizado Correctamente", servicio);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/servicios/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Servicio> servicio = repository.findById(id);
    if (servicio.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Servicio Eliminado Correctamente", servicio);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
