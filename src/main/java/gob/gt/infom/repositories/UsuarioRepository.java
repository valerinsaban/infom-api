package gob.gt.infom.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gob.gt.infom.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
  
  Optional<Usuario> findByUsuario(String usuario);

  @Query("select u from Usuario u where u.usuario = ?1")
  Optional<Usuario> getName(String usuario);
}
