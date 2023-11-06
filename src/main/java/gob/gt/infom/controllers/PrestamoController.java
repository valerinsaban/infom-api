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

  @GetMapping("/prestamos/estado/{estado}")
  public List<Prestamo> allByEstado(
      @PathVariable String estado) {
    return repository.findAllByEstado(estado);
  }

  @GetMapping("/prestamos/{fecha_inicio}/{fecha_fin}")
  public List<Prestamo> all(
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/{estado}/{fecha_inicio}/{fecha_fin}")
  public List<Prestamo> allByEstadoFecha(
      @PathVariable String estado,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.findAllByEstadoAndFechaBetween(estado, fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/count/fecha/{fecha_inicio}/{fecha_fin}")
  public Optional<Prestamo> countByFecha(
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByFechaBetween(fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/count/{estado}/{fecha_inicio}/{fecha_fin}")
  public Optional<Prestamo> countByEstado(
      @PathVariable String estado,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByEstadoAndFechaBetween(estado, fecha_inicio, fecha_fin);
  }

  @GetMapping("/prestamos/municipalidad/{estado}/{id_municipalidad}")
  public List<Prestamo> findAllByEstadoAndMunicipalidadId(
      @PathVariable String estado,
      @PathVariable Integer id_municipalidad) {
    return repository.findAllByEstadoAndMunicipalidadId(estado, id_municipalidad);
  }

  @GetMapping("/prestamos/programa/{estado}/{id_programa}")
  public List<Prestamo> findAllByEstadoAndProgramaId(
      @PathVariable String estado,
      @PathVariable Integer id_programa) {
    return repository.findAllByEstadoAndProgramaId(estado, id_programa);
  }

  @GetMapping("/prestamos/municipalidad/programa/{estado}/{id_municipalidad}/{id_programa}")
  public List<Prestamo> findAllByEstadoAndMunicipalidadIdAndProgramaId(
      @PathVariable String estado,
      @PathVariable Integer id_municipalidad,
      @PathVariable Integer id_programa) {
    return repository.findAllByEstadoAndMunicipalidadIdAndProgramaId(estado, id_municipalidad, id_programa);
  }

  @GetMapping("/prestamos/tipo_prestamo/{id_tipo_prestamo}/programa/{id_programa}/rango/{fecha_inicio}/{fecha_fin}")
  public Optional<Prestamo> countByTipoPrestamoIdAndProgramaIdAndFechaBetween(
      @PathVariable Integer id_tipo_prestamo,
      @PathVariable Integer id_programa,
      @PathVariable String fecha_inicio,
      @PathVariable String fecha_fin) {
    return repository.countByTipoPrestamoIdAndProgramaIdAndFechaBetween(id_tipo_prestamo, id_programa, fecha_inicio,
        fecha_fin);
  }

  @GetMapping("/prestamos/municipalidad/{id_municipalidad}")
  public Optional<Prestamo> countByMunicipalidadId(@PathVariable Integer id_municipalidad) {
    return repository.countByMunicipalidadId(id_municipalidad);
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
        .no_convenio(p.getNo_convenio())
        .no_prestamo(p.getNo_prestamo())
        .fecha(p.getFecha())
        .fecha_amortizacion(p.getFecha_amortizacion())
        .monto(p.getMonto())
        .plazo_meses(p.getPlazo_meses())
        .fecha_acta(p.getFecha_acta())
        .tasa(p.getTasa())
        .periodo_gracia(p.getPeriodo_gracia())
        .destino(p.getDestino())
        .no_destinos(p.getNo_destinos())
        .acta(p.getActa())
        .punto(p.getPunto())
        .fecha_memorial(p.getFecha_memorial())
        .certificacion(p.getCertificacion())
        .no_oficio_aj(p.getNo_oficio_aj())
        .fecha_oficio_aj(p.getFecha_oficio_aj())
        .no_oficio_ger(p.getNo_oficio_ger())
        .fecha_oficio_ger(p.getFecha_oficio_ger())
        .estado(p.getEstado())
        .id_tipo_prestamo(p.getId_tipo_prestamo())
        .id_programa(p.getId_programa())
        .id_municipalidad(p.getId_municipalidad())
        .id_resolucion(p.getId_resolucion())
        .id_funcionario(p.getId_funcionario())
        .id_regional(p.getId_regional())
        .id_usuario(p.getId_usuario())
        .build();
    repository.save(prestamo);
    return ResponseController.success("Prestamo Creado Correctamente", prestamo);
  }

  @PutMapping("/prestamos/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Prestamo p) {
    Optional<Prestamo> data = repository.findById(id);
    if (data.isPresent()) {
      Prestamo prestamo = data.get();
      prestamo.setNo_dictamen(p.getNo_dictamen());
      prestamo.setNo_convenio(p.getNo_convenio());
      prestamo.setNo_prestamo(p.getNo_prestamo());
      prestamo.setFecha(p.getFecha());
      prestamo.setFecha_amortizacion(p.getFecha_amortizacion());
      prestamo.setMonto(p.getMonto());
      prestamo.setPlazo_meses(p.getPlazo_meses());
      prestamo.setFecha_acta(p.getFecha_acta());
      prestamo.setTasa(p.getTasa());
      prestamo.setPeriodo_gracia(p.getPeriodo_gracia());
      prestamo.setDestino(p.getDestino());
      prestamo.setNo_destinos(p.getNo_destinos());
      prestamo.setActa(p.getActa());
      prestamo.setPunto(p.getPunto());
      prestamo.setFecha_memorial(p.getFecha_memorial());
      prestamo.setCertificacion(p.getCertificacion());
      prestamo.setNo_oficio_aj(p.getNo_oficio_aj());
      prestamo.setFecha_oficio_aj(p.getFecha_oficio_aj());
      prestamo.setNo_oficio_ger(p.getNo_oficio_ger());
      prestamo.setFecha_oficio_ger(p.getFecha_oficio_ger());
      prestamo.setEstado(p.getEstado());
      prestamo.setId_tipo_prestamo(p.getId_tipo_prestamo());
      prestamo.setId_programa(p.getId_programa());
      prestamo.setId_resolucion(p.getId_resolucion());
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
