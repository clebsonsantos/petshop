package com.crowde.fenrir.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowde.fenrir.model.Funcionario;
import com.crowde.fenrir.model.SexoPessoa;
import com.crowde.fenrir.model.TipoFuncionario;
import com.crowde.fenrir.repository.Estados;
import com.crowde.fenrir.repository.Funcionarios;
import com.crowde.fenrir.repository.Grupos;
import com.crowde.fenrir.service.CadastroFuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {

	private final String CADASTRO_FUNCIONARIO = "funcionario/cadastro-funcionario";
	private final String CONSULTAR_FUNCIONARIO = "funcionario/consulta-funcionario";
	
	@Autowired
	private CadastroFuncionarioService cadastroFuncionarioService; 
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private Grupos grupos;
	
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView(CONSULTAR_FUNCIONARIO);
		mv.addObject("funcionarios", funcionarios.findAll() );
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo(Funcionario funcionario) {

		ModelAndView mv = new ModelAndView(CADASTRO_FUNCIONARIO);
		mv.addObject("sexoPessoas", SexoPessoa.values());
		mv.addObject("estados", estados.findAll());
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("tiposFuncionario", TipoFuncionario.values());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result, Model model,
			RedirectAttributes attributes) {
		String redirecionamento = "redirect:/funcionarios/";
		if (result.hasErrors()) {
			return novo(funcionario);
		}

		if(!funcionario.isNovo()) {
			redirecionamento += funcionario.getId();
		}else {
			redirecionamento+="novo";
		}
		cadastroFuncionarioService.salvar(funcionario);
		
		attributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso!");

		return new ModelAndView(redirecionamento);
	}
	

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {

		Funcionario funcionario = funcionarios.findOne(id);
		ModelAndView mv = novo(funcionario);
		mv.addObject(funcionario);
		return mv;
	}

}
