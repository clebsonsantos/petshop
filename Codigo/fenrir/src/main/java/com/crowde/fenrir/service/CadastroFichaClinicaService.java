package com.crowde.fenrir.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.FichaClinica;
import com.crowde.fenrir.model.SituacaoConsulta;
import com.crowde.fenrir.repository.Consultas;
import com.crowde.fenrir.repository.FichasClinicas;

@Service
public class CadastroFichaClinicaService {

	@Autowired
	private FichasClinicas fichas;
	
	@Autowired
	private Consultas consultas;
	
	@Transactional
	public void salvar(FichaClinica ficha) {
		fichas.desativarFichas(ficha.getConsulta());
		fichas.save(ficha);
		Consulta con = ficha.getConsulta();
		con.setSituacao(SituacaoConsulta.ATENDIDO);
		consultas.update(con);
	}
	
}
