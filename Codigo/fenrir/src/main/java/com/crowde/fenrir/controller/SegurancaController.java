package com.crowde.fenrir.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegurancaController {

	private final String LOGIN = "Login";
	private final String ACESSO_NEGADO = "403";

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/consultas";
		}
		return LOGIN;
	}

	@GetMapping("/403")
	public String acessoNegado() {
		return ACESSO_NEGADO;
	}

}
