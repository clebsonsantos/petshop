package com.crowde.fenrir.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowde.fenrir.model.Animal;
import com.crowde.fenrir.model.Cliente;
import com.crowde.fenrir.model.SexoAnimal;
import com.crowde.fenrir.repository.Animais;
import com.crowde.fenrir.repository.Clientes;
import com.crowde.fenrir.repository.Cores;
import com.crowde.fenrir.repository.Especies;
import com.crowde.fenrir.service.CadastroAnimalService;

@Controller
@RequestMapping("/animais")
public class AnimaisController {

	private final String CADASTRO_ANIMAL = "animal/cadastro-animal";
	private final String CONSULTAR_ANIMAL = "animal/consulta-animal";

	Cliente cliente;

	@Autowired
	private Clientes clientes;

	@Autowired
	private Animais animais;

	private Long id;

	@Autowired
	private CadastroAnimalService cadastroAnimalService;

	@Autowired
	private Cores cores;

	@Autowired
	private Especies especies;

	@RequestMapping
	public String index() {
		return CONSULTAR_ANIMAL;
	}

	@RequestMapping("/novo/{idCliente}")
	public ModelAndView novo(Animal animal, @PathVariable("idCliente") Long idCliente) {

		this.id = idCliente;
		cliente = new Cliente();
		if (StringUtils.isEmpty(idCliente)) 
			return new ModelAndView("redirect:/clientes/");
		
		cliente = clientes.findOne(this.id);

		ModelAndView mv = new ModelAndView(CADASTRO_ANIMAL);
		mv.addObject("cores", cores.findAll());
		mv.addObject("sexoAnimais", SexoAnimal.values());
		mv.addObject("especies", especies.findAll());
		mv.addObject("cliente", cliente);

		return mv;
	}

	@RequestMapping(value = { "/novo/{\\d+}", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Animal animal, BindingResult result, Model model, RedirectAttributes attributes) {

		String redirecionamento = "redirect:/animais/" + animal.getId();
		
		
		validarCampos(animal, result);
		
		if (result.hasErrors()) {

			return novo(animal, id);
		}
		
		animal.setCliente(cliente);

		if (animal.isNovo()) {			
			redirecionamento = "redirect:/animais/novo/" + cliente.getId();
		}

		attributes.addFlashAttribute("mensagem", "Animal salvo com sucesso!");
		cadastroAnimalService.salvar(animal);
		
		return new ModelAndView(redirecionamento);
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {

		Animal animal = animais.findOne(id);
		ModelAndView mv = novo(animal, animal.getCliente().getId());
		mv.addObject(animal);
		return mv;

	}
	
	private void validarCampos(Animal animal, BindingResult result) {
		
		if (StringUtils.isEmpty(animal.getRaca())) {
			result.rejectValue("raca", "Raça é obrigatório", "Raça é obrigatório");			
		}
		
		if(StringUtils.isEmpty(animal.getRaca()) || StringUtils.isEmpty(animal.getRaca().getEspecie()) || StringUtils.isEmpty(animal.getRaca().getEspecie().getId())) {			
			result.rejectValue("raca.especie","animal.raca.especie", "Espécie é obrigatório");
		}
		
	}
	

}
