package com.crowde.fenrir.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.crowde.fenrir.model.Funcionario;
import com.crowde.fenrir.model.Usuario;

public class UsuarioAutenticado {

	public static Usuario getUsuarioAutenticado() {
		UsuarioSistema usuarioLogado = (UsuarioSistema) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioLogado.getUsuario();
	}

	public static Long getIdUsuarioAutenticado() {
		return getUsuarioAutenticado().getId();
	}

	public static Funcionario getFuncionarioAutenticado() {
		return getUsuarioAutenticado().getFuncionario();
	}

	public static Long getIdFuncionarioAutenticado() {
		return getUsuarioAutenticado().getFuncionario().getId();
	}

}
