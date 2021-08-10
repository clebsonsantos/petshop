package com.crowde.fenrir.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Prontuario;
import com.crowde.fenrir.repository.Prontuarios;

@Service
public class CadastroProntuarioService {

	@Autowired
	private Prontuarios prontuarios;
	
	public List<Prontuario> todas() {
		return prontuarios.findAll();
	}
	
	@Transactional
	public void salvar(Prontuario prontuario) {
		prontuarios.saveAndFlush(prontuario);
	}
}
