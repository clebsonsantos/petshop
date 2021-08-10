package com.crowde.fenrir.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.crowde.fenrir.model.Usuario;

public interface UsuariosQueries {
	public Optional<Usuario> porLoginEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
}
