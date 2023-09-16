package gob.gt.infom.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gob.gt.infom.models.Departamento;
import gob.gt.infom.models.Prestamo;
import gob.gt.infom.repositories.DepartamentoRepository;
import gob.gt.infom.repositories.PrestamoRepository;
import io.jsonwebtoken.io.IOException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class ReporteController {

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private DepartamentoRepository departamentoRepository;

  @Autowired
  private PrestamoRepository prestamoRepository;

  @GetMapping("/reportes/{formato}/departamentos")
  public ResponseEntity<?> reporteDepartamentos(@PathVariable String formato) throws JRException, IOException {
    try {

      Iterable<Departamento> departamentos = departamentoRepository.findAll();
      List<Departamento> list = new ArrayList<Departamento>();
      departamentos.forEach(list::add);

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("reporte", "Reporte de Departamentos");
      params.put("data", new JRBeanCollectionDataSource(list));

      Resource resource = resourceLoader.getResource("classpath:reports/departamentos.jrxml");
      JasperReport jr = JasperCompileManager.compileReport(resource.getInputStream());
      JasperPrint report = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());

      switch (formato) {
        case "PDF":
          return ResponseController.pdf(report, "departamentos");
        case "XLS":
          return ResponseController.xls(report, "departamentos");
        default:
          return ResponseController.error("Formato invalido", null);
      }

    } catch (Exception e) {
      return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/reportes/{formato}/prestamo/resumen/{id}")
  public ResponseEntity<?> reportePrestamoResumen(@PathVariable String formato, @PathVariable Integer id)
      throws JRException, IOException {
    try {

      Optional<Prestamo> prestamo = prestamoRepository.findById(id);

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("data", prestamo);
      // params.put("no_dictamen", prestamo.get().getNo_dictamen());
      // params.put("no_pagare", prestamo.get().getNo_pagare());
      params.put("fecha", prestamo.get().getFecha());
      params.put("pagina", "01");
      params.put("resolucion", "3424242");
      params.put("convenio", "89787");

      // params.put("fecha_vencimiento", prestamo.get().getFecha_vencimiento());
      // params.put("monto", prestamo.get().getMonto());
      // params.put("plazo_meses", prestamo.get().getPlazo_meses());
      // params.put("fecha_acta", prestamo.get().getFecha_acta());
      // params.put("deposito_intereses", prestamo.get().getDeposito_intereses());
      // params.put("intereses", prestamo.get().getIntereses());
      // params.put("intereses_fecha_fin", prestamo.get().getIntereses_fecha_fin());

      Resource resource = resourceLoader.getResource("classpath:reports/prestamo-resumen.jrxml");
      JasperReport jr = JasperCompileManager.compileReport(resource.getInputStream());
      JasperPrint report = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());

      switch (formato) {
        case "PDF":
          return ResponseController.pdf(report, "departamentos");
        case "XLS":
          return ResponseController.xls(report, "departamentos");
        default:
          return ResponseController.error("Formato invalido", null);
      }

    } catch (Exception e) {
      return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/reportes/{formato}/maqueta")
  public ResponseEntity<?> reporteMaqueta(@PathVariable String formato) throws JRException, IOException {
    try {

      Resource resource = resourceLoader.getResource("classpath:reports/maqueta.jrxml");
      JasperReport jr = JasperCompileManager.compileReport(resource.getInputStream());
      JasperPrint report = JasperFillManager.fillReport(jr, null, new JREmptyDataSource());

      // // Visualizando el Reporte
      // JasperViewer viewer = new JasperViewer(re, false);
      // viewer.setTitle("REPORTE DE CLIENTES");
      // viewer.setVisible(true);

      switch (formato) {
        case "PDF":
          return ResponseController.pdf(report, "Mequeta");
        case "XLS":
          return ResponseController.xls(report, "Mequeta");
        default:
          return ResponseController.error("Formato invalido", null);
      }

    } catch (Exception e) {
      return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
