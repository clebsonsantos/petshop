package com.crowde.fenrir.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.repository.Consultas;
import com.crowde.fenrir.security.UsuarioAutenticado;
import com.crowde.fenrir.service.exception.ImpossivelExluirEntidade;


@Service
public class CadastroConsultaService {

	@Autowired
	private Consultas consultas;

	public List<Consulta> todas() {
		return consultas.findAll();
	}

	@Transactional
	public void salvar(Consulta consulta) {

		try {
			consulta.setAtendente(UsuarioAutenticado.getFuncionarioAutenticado());
			if (consulta.isNova()) {
				consulta.setDataCadastro(LocalDateTime.now());
				consultas.save(consulta);
			} else {
				consultas.update(consulta);
			}
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void finalizar(Consulta consulta) {
		consultas.finalizar(consulta);
	}

	@Transactional
	public void cancelar(Long id) {
		try {
			
			consultas.cancelar(id);

		} catch (PersistenceException e) {
			throw new ImpossivelExluirEntidade("Impossível canclear a consulta.");
		}
	}

}
