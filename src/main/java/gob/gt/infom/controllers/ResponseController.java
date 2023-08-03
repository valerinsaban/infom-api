package gob.gt.infom.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public class ResponseController {

  @ResponseBody
  public static ResponseEntity<Object> success(String mensaje, Object data) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("resultado", true);
    map.put("mensaje", mensaje);
    map.put("data", data);
    return new ResponseEntity<Object>(map, HttpStatus.OK);
  }

  // @ResponseBody
  // public ResponseEntity<Object> error(String mensaje, Exception data) {
  //   Map<String, Object> map = new HashMap<String, Object>();
  //   map.put("resultado", false);
  //   map.put("mensaje", mensaje);
  //   map.put("data", data);
  //   return new ResponseEntity<Object>(map, HttpStatus.OK);
  // }

}
