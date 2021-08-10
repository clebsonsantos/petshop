package com.crowde.fenrir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Usuario;
import com.crowde.fenrir.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries{
	public Optional<Usuario> findByLogin(String login);
	
}
