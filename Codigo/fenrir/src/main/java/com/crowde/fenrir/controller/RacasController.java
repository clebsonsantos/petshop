package com.crowde.fenrir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crowde.fenrir.model.Raca;
import com.crowde.fenrir.repository.Racas;

@Controller
@RequestMapping("/racas")
public class RacasController {

	@Autowired
	private Racas racas;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Raca> pesquisarPorCodigoEspecie(
			@RequestParam(name = "especie", defaultValue = "-1") Long idEspecie) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) { }
		return racas.findByEspecieId(idEspecie);
	};

}
