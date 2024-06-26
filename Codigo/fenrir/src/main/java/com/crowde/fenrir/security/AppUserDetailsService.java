package com.crowde.fenrir.security;

import java.util.logging.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crowde.fenrir.model.Usuario;
import com.crowde.fenrir.repository.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;
	
	private static final Logger logger = Logger.getLogger(AppUserDetailsService.class.getName());
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional =	usuarios.porLoginEAtivo(login);		
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
        logger.info("Iniciando checagem de permissões do usuário.");

		
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}


	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		List<String> permissoes =  usuarios.permissoes(usuario);		

		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		return authorities;
	}

}
