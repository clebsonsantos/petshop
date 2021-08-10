package com.crowde.fenrir.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowde.fenrir.controller.validator.FichaClinicaValidator;
import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.FichaClinica;
import com.crowde.fenrir.repository.Consultas;
import com.crowde.fenrir.repository.FichasClinicas;
import com.crowde.fenrir.service.CadastroFichaClinicaService;

@Controller
@RequestMapping("/fichas")
public class FichaClinicaController {
	
	private final String CRIAR_FICHA = "ficha/cadastro-ficha";
	private Long idConsulta;
	
	@Autowired
	private Consultas consultas;
	

	@Autowired
	private FichasClinicas fichas;
	

	
	@Autowired
	private CadastroFichaClinicaService cadastroFichaClinicaService;
	
	private FichaClinicaValidator val = new FichaClinicaValidator();
	
	@RequestMapping(value = "/{consultaId}", method = RequestMethod.GET)
	public ModelAndView gerenciarRedirecionamento(@PathVariable("consultaId") Long id) {
		Consulta con = consultas.findOne(id);
		this.idConsulta = id;
		
		FichaClinica ficha = fichas.findFichaByConsulta(con);
		
		ModelAndView mv;
		if (ficha.isNova()) {
			mv = nova(con);
		} else {
			mv = nova(ficha);
		}
		
		return mv;
		
	}

	public ModelAndView nova(Consulta con) {
		
		ModelAndView mv = new ModelAndView(CRIAR_FICHA);
		mv.addObject("consulta", con);
		mv.addObject("fichaClinica", new FichaClinica());
		return mv;
	}


	@RequestMapping("/novo")	
	public ModelAndView nova(FichaClinica ficha) {
		
			ModelAndView mv = new ModelAndView(CRIAR_FICHA);
			mv.addObject("fichaClinica", ficha);
			mv.addObject("consulta", ficha.getConsulta());
			return mv;
		}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid FichaClinica fichaClinica, BindingResult result, Model model, 
			RedirectAttributes attributes) {
	
		String urlRedirecionamento = "redirect:/fichas/";
		
		val.validarCadastro(fichaClinica, result);
		
		if (result.hasErrors()) {
			Consulta con = consultas.findOne(idConsulta);
			ModelAndView mv = new ModelAndView(CRIAR_FICHA);
			mv.addObject("consulta", con);
			mv.addObject("fichaClinica", fichaClinica);
			return mv;
		}
		Consulta con = consultas.findOne(idConsulta);
		
		fichaClinica.setConsulta(con);
		fichaClinica.setProntuario(con.getProntuario());
		fichaClinica.setDataCadastro(LocalDateTime.now());
		fichaClinica.setAtiva(true);
		cadastroFichaClinicaService.salvar(fichaClinica);
		
		urlRedirecionamento = urlRedirecionamento + fichaClinica.getConsulta().getId().toString();
		attributes.addFlashAttribute("mensagem", "Ficha Clínica salva com sucesso! Clique no botão Imprimir para imprimir.");
		
		return new ModelAndView(urlRedirecionamento);
	}

}
