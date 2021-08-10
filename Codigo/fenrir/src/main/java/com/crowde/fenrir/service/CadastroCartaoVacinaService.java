package com.crowde.fenrir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.CartaoVacina;
import com.crowde.fenrir.repository.CartaoVacinas;

@Service
public class CadastroCartaoVacinaService {

	@Autowired
	private CartaoVacinas cartaoVacinas;

	@Transactional
	public void salvar(CartaoVacina cartaovacina) {

		//Optional<Cor> corOptional = cores.findByDescricaoIgnoreCase(vartaovacina.getDescricao());
		//if(corOptional.isPresent()) {
			//throw new DadosException("Cor Já cadastrada");
		//}
		
		cartaoVacinas.save(cartaovacina);
	}

}
