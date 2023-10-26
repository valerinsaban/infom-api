package gob.gt.infom.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gob.gt.infom.models.Usuario;
import gob.gt.infom.repositories.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UsuarioRepository repository;

  @GetMapping("/usuarios")
  public Iterable<Usuario> all() {
    return repository.findAll();
  }

  @GetMapping("/usuarios/{id}")
  public Optional<Usuario> one(@PathVariable Integer id) {
    return repository.findById(id);
  }

  @GetMapping("/usuarios/usuario/{usuario}")
  public Optional<Usuario> oneByUsuario(@PathVariable String usuario) {
    return repository.findByUsuario(usuario);
  }

  @PostMapping("/usuarios")
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody @Valid Usuario u) {
    Usuario usuario = Usuario.builder()
        .nombre(u.getNombre())
        .apellido(u.getApellido())
        .dpi(u.getDpi())
        .usuario(u.getUsuario())
        .clave(passwordEncoder.encode(u.getClave()))
        .acceso(u.getAcceso())
        .id_regional(u.getId_regional())
        .id_rol(u.getId_rol())
        .build();
    repository.save(usuario);
    return ResponseController.success("Usuario Agregado Correctamente", usuario);
  }

  @PutMapping("/usuarios/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Usuario u) {
    Optional<Usuario> data = repository.findById(id);
    if (data.isPresent()) {
      Usuario usuario = data.get();
      usuario.setNombre(u.getNombre());
      usuario.setApellido(u.getApellido());
      usuario.setDpi(u.getDpi());
      usuario.setUsuario(u.getUsuario());
      // usuario.setClave(passwordEncoder.encode(u.getClave()));
      usuario.setAcceso(u.getAcceso());
      usuario.setId_regional(u.getId_regional());
      usuario.setId_rol(u.getId_rol());
      repository.save(usuario);
      return ResponseController.success("Usuario Actualizado Correctamente", usuario);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/usuarios/clave/{id}")
  public ResponseEntity<?> updateClave(@PathVariable("id") Integer id, @RequestBody Usuario u) {
    Optional<Usuario> data = repository.findById(id);
    if (data.isPresent()) {
      Usuario usuario = data.get();
      usuario.setClave(passwordEncoder.encode(u.getClave())); 
      repository.save(usuario);
      return ResponseController.success("Usuario Actualizado Correctamente", usuario);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/usuarios/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    Optional<Usuario> data = repository.findById(id);
    if (data.isPresent()) {
      repository.deleteById(id);
      return ResponseController.success("Usuario Eliminado Correctamente", null);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
