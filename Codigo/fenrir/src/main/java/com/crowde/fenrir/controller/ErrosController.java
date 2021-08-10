package com.crowde.fenrir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrosController {
	private final String PAGINA_NAO_ENCONTRADA = "404";
	private final String ERRO_SERVIDOR_500 = "500";
	private final String ERRO_SERVIDOR_400 = "400";
	
	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return PAGINA_NAO_ENCONTRADA;
	}
	
	@RequestMapping("/500")
	public String erroServidor500() {
		return ERRO_SERVIDOR_500;
	}
	
	@RequestMapping("/400")
	public String erroServidor400() {
		return ERRO_SERVIDOR_400;
	}
	
}