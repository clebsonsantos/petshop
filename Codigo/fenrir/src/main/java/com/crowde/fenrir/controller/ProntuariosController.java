package com.crowde.fenrir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crowde.fenrir.model.Animal;
import com.crowde.fenrir.repository.Animais;


@Controller
@RequestMapping("/prontuarios")
public class ProntuariosController {
	@Autowired
	private Animais animais;
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Animal> pesquisar(
			@RequestParam(name = "filtro", defaultValue = "-1") String filtro,@RequestParam(name = "descricao", defaultValue = "-1") String descricao) {
		
		
		
		validarTamanhoDescricao(descricao);
		
		switch (filtro) {
			case "cliente": return animais.findByClienteNomeStartingWithIgnoreCase(descricao);
			case "animal": return animais.findByNomeStartingWithIgnoreCase(descricao);
			case "prontuario": return animais.findByProntuarioMatriculaStartingWithIgnoreCase(descricao);
			case "cpf": return animais.findByClienteCpfCnpjStartingWithIgnoreCase(descricao);
			case "telefone": return animais.findByClienteTelefoneCelularStartingWithIgnoreCase(descricao);		
		}
		
		return null;
	}

	private void validarTamanhoDescricao(String descricao) {
		if(StringUtils.isEmpty(descricao) || descricao.length()< 3) {
			throw new IllegalArgumentException();
		}		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e){
		return ResponseEntity.badRequest().build();
	}
	
	
	

}
