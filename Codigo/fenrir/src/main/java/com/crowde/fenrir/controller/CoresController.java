package com.crowde.fenrir.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crowde.fenrir.model.Cor;
import com.crowde.fenrir.service.CadastroCorService;

@Controller
public class CoresController {

	@Autowired
	private CadastroCorService cadastroCorService;

	@RequestMapping(value = "cores", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Cor cor, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
		cor.setDataCadastro(new Date());

		cadastroCorService.salvar(cor);

		return ResponseEntity.ok(cor);
	}

}
