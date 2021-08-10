package com.crowde.fenrir.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crowde.fenrir.model.Logradouro;
import com.crowde.fenrir.repository.Logradouros;
import com.crowde.fenrir.service.exception.DadosException;

@Controller
@RequestMapping("/logradouros")
public class LogradourosController {

	@Autowired
	private Logradouros logradouros;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Logradouro pesquisarPorCodigoEspecie(
			@RequestParam(name = "logradouro", defaultValue = "null") String cep) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		Optional<Logradouro> logradouroOptional = logradouros.findByCep(cep);
		if (logradouroOptional.isPresent()) {
			return logradouroOptional.get();
		}

		throw new DadosException("Nenhum endereço encontrado");

	};

}
