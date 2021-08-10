package com.crowde.fenrir.controller;

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

import com.crowde.fenrir.model.Funcionario;
import com.crowde.fenrir.model.Usuario;
import com.crowde.fenrir.repository.Funcionarios;
import com.crowde.fenrir.repository.Grupos;
import com.crowde.fenrir.service.CadastroUsuarioService;
import com.crowde.fenrir.service.exception.LoginUsuarioJaCadastradoException;
import com.crowde.fenrir.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	private final String CADASTRO_USUARIO = "usuario/cadastro-usuario";

	@Autowired
	private Grupos grupos;

	@Autowired
	private Funcionarios funcionarios;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	private Funcionario funcionario;

	private Long id;

	@RequestMapping
	public String index() {
		return null;
	}

	@RequestMapping("/novo/{funcionarioId}")
	public ModelAndView novo(Usuario usuario, @PathVariable(value = "funcionarioId") Long id) {

		this.id = id;
		funcionario = new Funcionario();
		funcionario = funcionarios.findOne(this.id);		
		if(usuario.isNovo()) {
			usuario.setAtivo(true);
		}
		
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO);
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("funcionario", funcionario);
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return novo(usuario, id);
		}

		usuario.setFuncionario(funcionario);
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (LoginUsuarioJaCadastradoException e) {
			result.rejectValue("login", e.getMessage(), e.getMessage());
			return novo(usuario, this.id);
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario, this.id);
		}
		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso!");

		return new ModelAndView("redirect:/usuarios/novo/" + this.id);
	}

}
