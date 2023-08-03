package gob.gt.infom.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gob.gt.infom.models.Usuario;
import gob.gt.infom.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Usuario userEntity = userRepository.findByUsuario(username)
        .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

    Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getNombre().name())))
        .collect(Collectors.toSet());

    return new User(userEntity.getUsuario(),
        userEntity.getClave(),
        true,
        true,
        true,
        true,
        authorities);
  }
}
