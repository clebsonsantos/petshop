package com.crowde.fenrir.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowde.fenrir.controller.page.PageWrapper;
import com.crowde.fenrir.model.Cliente;
import com.crowde.fenrir.model.SexoAnimal;
import com.crowde.fenrir.model.SexoPessoa;
import com.crowde.fenrir.model.TipoCliente;
import com.crowde.fenrir.repository.Clientes;
import com.crowde.fenrir.repository.Estados;
import com.crowde.fenrir.repository.filter.ClienteFilter;
import com.crowde.fenrir.service.CadastroClienteService;
import com.crowde.fenrir.service.exception.ImpossivelExluirEntidade;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	private final String CADASTRO_CLIENTE = "cliente/cadastro-cliente";
	private final String CONSULTAR_CLIENTE = "cliente/consulta-cliente";
	private final String PRONTUARIO_CLIENTE = "cliente/prontuario-cliente";

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@Autowired
	private Clientes clientes;

	@Autowired
	private Estados estados;

	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {

		ModelAndView mv = new ModelAndView(CADASTRO_CLIENTE);
		mv.addObject("sexoPessoas", SexoPessoa.values());
		mv.addObject("sexoAnimais", SexoAnimal.values());
		mv.addObject("tipoPessoas", TipoCliente.values());
		mv.addObject("estados", estados.findAll());

		return mv;
	}

	@GetMapping("/prontuarios/{id}")
	public ModelAndView prontuario(@PathVariable("id") Long id) {

		Cliente cliente = clientes.findOne(id);

		ModelAndView mv = new ModelAndView(PRONTUARIO_CLIENTE);
		mv.addObject("cliente", cliente);

		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, Model model,
			RedirectAttributes attributes) {
		String redirecionamento = "redirect:/clientes/";
		if (result.hasErrors()) {
			return novo(cliente);
		}

		if (cliente.isNovo()) {
			redirecionamento = "redirect:/animais/novo/";
		}

		cadastroClienteService.salvar(cliente);
		redirecionamento += cliente.getId();

		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");

		return new ModelAndView(redirecionamento);
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {

		Cliente cliente = clientes.findOne(id);
		ModelAndView mv = novo(cliente);
		mv.addObject(cliente);
		return mv;
	}

	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroClienteService.excluir(id);
		} catch (ImpossivelExluirEntidade e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result,
			@PageableDefault(size = 4) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(CONSULTAR_CLIENTE);
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable),httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}
