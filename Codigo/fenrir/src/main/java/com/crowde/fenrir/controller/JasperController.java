package com.crowde.fenrir.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowde.fenrir.model.FichaClinica;

@Controller
@RequestMapping("/relatorios")
public class JasperController {
	
	@RequestMapping("/fichaClinica/{idConsulta}")
	public ModelAndView imprimirFichaClinica(@PathVariable("idConsulta") Long idConsulta) {
		
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("format", "pdf");
		parametros.put("idConsulta", idConsulta);
	
		return new ModelAndView("relatorio_ficha_clinica", parametros);
	}
}
