package com.crowde.fenrir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inicios")
public class InicioController {
	
	private final String DASHBOARD= "inicio/dashboard";

	@RequestMapping
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView(DASHBOARD);

		

		return mv;
	}
	
}
