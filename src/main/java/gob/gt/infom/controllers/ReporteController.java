package gob.gt.infom.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.gt.infom.models.Departamento;
import gob.gt.infom.repositories.DepartamentoRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
public class ReporteController {

  @Autowired
  private DepartamentoRepository repository;

  @GetMapping("/reportes/departamentos/PDF")
  public ResponseEntity<byte[]> reporteDepartamentosPDF() {
    try {

      Iterable<Departamento> departamentos = repository.findAll();
      List<Departamento> dList = new ArrayList<Departamento>();
      departamentos.forEach(dList::add);

      Map<String, Object> empParams = new HashMap<String, Object>();
      empParams.put("reporte", "Reporte de Departamentos");
      empParams.put("data", new JRBeanCollectionDataSource(dList));

      JasperPrint empReport = JasperFillManager.fillReport(
          JasperCompileManager.compileReport(
              ResourceUtils.getFile("classpath:reports/departamentos.jrxml").getAbsolutePath()),
          empParams, new JREmptyDataSource());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);
      headers.setContentDispositionFormData("filename", "departamentos.pdf");

      return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/reportes/departamentos/XLS")
  public ResponseEntity<byte[]> reporteDepartamentosXLS() {
    try {

      Iterable<Departamento> departamentos = repository.findAll();
      List<Departamento> dList = new ArrayList<Departamento>();
      departamentos.forEach(dList::add);

      Map<String, Object> empParams = new HashMap<String, Object>();
      empParams.put("reporte", "Reporte de Departamentos");
      empParams.put("data", new JRBeanCollectionDataSource(dList));

      JasperPrint empReport = JasperFillManager.fillReport(
          JasperCompileManager.compileReport(
              ResourceUtils.getFile("classpath:reports/departamentos.jrxml").getAbsolutePath()),
          empParams, new JREmptyDataSource());

      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type",
          "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
      var contentDisposition = ContentDisposition
          .builder("attachment")
          .filename("departamentos.xls")
          .build();
      headers.setContentDisposition(contentDisposition);

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      JRXlsxExporter xlsEexporter = new JRXlsxExporter();
      xlsEexporter.setExporterInput(new SimpleExporterInput(empReport));
      xlsEexporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
      SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
      xlsEexporter.setConfiguration(config);
      xlsEexporter.exportReport();

      return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);

    } catch (Exception e) {
      throw new RuntimeException(e);

    }

  }

}
