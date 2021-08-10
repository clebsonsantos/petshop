package com.crowde.fenrir.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Cor;
import com.crowde.fenrir.repository.Cores;
import com.crowde.fenrir.service.exception.DadosException;

@Service
public class CadastroCorService {

	@Autowired
	private Cores cores;

	@Transactional
	public void salvar(Cor cor) {

		Optional<Cor> corOptional = cores.findByDescricaoIgnoreCase(cor.getDescricao());
		if(corOptional.isPresent()) {
			throw new DadosException("Cor Já cadastrada");
		}
		
		cores.save(cor);
	}

}
