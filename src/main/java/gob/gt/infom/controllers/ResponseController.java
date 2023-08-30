package gob.gt.infom.controllers;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class ResponseController {

  @ResponseBody
  public static ResponseEntity<Object> success(String mensaje, Object data) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("resultado", true);
    map.put("mensaje", mensaje);
    map.put("data", data);
    return new ResponseEntity<Object>(map, HttpStatus.OK);
  }

  public static ResponseEntity<Object> error(String mensaje, Exception data) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("resultado", false);
    map.put("mensaje", mensaje);
    map.put("data", data);
    return new ResponseEntity<Object>(map, HttpStatus.OK);
  }

  public static ResponseEntity<byte[]> pdf(JasperPrint report, String filename) throws JRException {
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    HttpHeaders headers = new HttpHeaders();

    JasperExportManager.exportReportToPdfStream(report, outStream);

    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    headers.add("content-disposition", "inline;filename=" + filename + ".pdf");

    return new ResponseEntity<byte[]>(outStream.toByteArray(), headers, HttpStatus.OK);
  }

  public static ResponseEntity<byte[]> xls(JasperPrint report, String filename) throws JRException {
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    HttpHeaders headers = new HttpHeaders();

    headers.set("Content-Type",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
    var contentDisposition = ContentDisposition
        .builder("attachment")
        .filename(filename + ".xls")
        .build();
    headers.setContentDisposition(contentDisposition);

    JRXlsxExporter xlsEexporter = new JRXlsxExporter();
    xlsEexporter.setExporterInput(new SimpleExporterInput(report));
    xlsEexporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
    SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
    xlsEexporter.setConfiguration(config);
    xlsEexporter.exportReport();

    return new ResponseEntity<byte[]>(outStream.toByteArray(), headers, HttpStatus.OK);
  }

}
