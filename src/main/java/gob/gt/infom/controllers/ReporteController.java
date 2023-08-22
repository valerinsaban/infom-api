package gob.gt.infom.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.gt.infom.models.Departamento;
import gob.gt.infom.models.Municipio;
import gob.gt.infom.repositories.DepartamentoRepository;
import gob.gt.infom.repositories.MunicipioRepository;
import io.jsonwebtoken.io.IOException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
public class ReporteController {

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private DepartamentoRepository departamentoRepository;

  @Autowired
  private MunicipioRepository municipioRepository;

  @GetMapping("/reportes/departamentos/PDF")
  public ResponseEntity<byte[]> reporteDepartamentosPDF() throws JRException, IOException {
    try {

      Iterable<Departamento> departamentos = departamentoRepository.findAll();
      List<Departamento> dList = new ArrayList<Departamento>();
      departamentos.forEach(dList::add);

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("reporte", "Reporte de Departamentos");
      params.put("data", new JRBeanCollectionDataSource(dList));

      Resource resource = resourceLoader.getResource("classpath:reports/departamentos.jrxml");
      JasperReport jr = JasperCompileManager.compileReport(resource.getInputStream());
      JasperPrint report = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());

      final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      JasperExportManager.exportReportToPdfStream(report, outStream);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.parseMediaType("application/pdf"));
      headers.add("content-disposition", "inline;filename=departamentos.pdf");

      return new ResponseEntity<byte[]>(outStream.toByteArray(), headers, HttpStatus.OK);

      // HttpHeaders headers = new HttpHeaders();
      // headers.setContentType(MediaType.APPLICATION_PDF);
      // headers.setContentDispositionFormData("filename", "departamentos.pdf");

      // return new
      // ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(report),
      // headers, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/reportes/departamentos/XLS")
  public ResponseEntity<byte[]> reporteDepartamentosXLS() {
    try {

      Iterable<Departamento> departamentos = departamentoRepository.findAll();
      List<Departamento> dList = new ArrayList<Departamento>();
      departamentos.forEach(dList::add);

      Map<String, Object> empParams = new HashMap<String, Object>();
      empParams.put("reporte", "Reportes de Departamentos");
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

  @GetMapping("/reportes/municipios/PDF")
  public ResponseEntity<byte[]> reporteMunicipiosPDF() {
    try {

      Iterable<Municipio> municipios = municipioRepository.findAll();
      List<Municipio> list = new ArrayList<Municipio>();
      municipios.forEach(list::add);

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("reporte", "Reporte de Municipios");
      params.put("data", new JRBeanCollectionDataSource(list));

      JasperPrint report = JasperFillManager.fillReport(
          JasperCompileManager.compileReport(
              ResourceUtils.getFile("classpath:reports/municipios.jrxml").getAbsolutePath()),
          params, new JREmptyDataSource());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);
      headers.setContentDispositionFormData("filename", "municipios.pdf");

      return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(report), headers, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
