package com.crowde.fenrir.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Usuario;
import com.crowde.fenrir.repository.Usuarios;
import com.crowde.fenrir.service.exception.LoginUsuarioJaCadastradoException;
import com.crowde.fenrir.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> todos() {
		return usuarios.findAll();
	}

	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioOp = usuarios.findByLogin(usuario.getLogin());
		if(usuarioOp.isPresent()) {
			throw new LoginUsuarioJaCadastradoException("Login já cadastrado");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if(usuario.isNovo()) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuario.setDataCadastro(LocalDateTime.now());	
		usuario.setDataAlteracao(LocalDateTime.now());	
		usuarios.save(usuario);
		
	}
	
}
