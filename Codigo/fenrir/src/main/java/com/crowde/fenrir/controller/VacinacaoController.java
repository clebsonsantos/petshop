package com.crowde.fenrir.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crowde.fenrir.model.Animal;
import com.crowde.fenrir.model.CartaoVacina;
import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.StatusVacina;
import com.crowde.fenrir.model.Vacina;
import com.crowde.fenrir.model.json.CartaoVacinaJson;
import com.crowde.fenrir.repository.Animais;
import com.crowde.fenrir.repository.Consultas;
import com.crowde.fenrir.repository.Vacinas;
import com.crowde.fenrir.service.CadastroCartaoVacinaService;

@Controller
@RequestMapping("/vacinacoes")
public class VacinacaoController {

	private final String VACINACAO = "cartaoVacina/cartao-vacina";

	@Autowired
	private Animais animais;

	@Autowired
	private Consultas consultas;

	@Autowired
	private Vacinas vacinas;

	@Autowired
	private CadastroCartaoVacinaService cadastroCartaoVacinaService;

	@RequestMapping("/{id}")
	public ModelAndView index(@PathVariable Long id) {
		Consulta consulta = consultas.findOne(id);
		Animal animal = consulta.getProntuario().getAnimal();

		List<Vacina> vacinasList = vacinas.findAllByEspecie(animal.getRaca().getEspecie());
		

		ModelAndView mv = new ModelAndView(VACINACAO);
		mv.addObject("animal", animal);
		mv.addObject("consulta", consulta);
		mv.addObject("vacinas", vacinasList);
		return mv;
	}

	@RequestMapping(value = "vacinar", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView salvar(@RequestBody @Valid CartaoVacinaJson cartaoVacinaJson, BindingResult result) {
		ModelAndView mv;
		if (result.hasErrors()) {
			List<String> mensagens = new ArrayList<>();
			if (result.getFieldError("idVacina") != null) {
				mensagens.add(result.getFieldError("idVacina").getDefaultMessage());				
			}

			if (result.getFieldError("dose") != null) {
				mensagens.add(result.getFieldError("dose").getDefaultMessage());
			}
			mv = new ModelAndView("cartaoVacina/error");
			mv.addObject("mensagens", mensagens);
			return mv;
		}

		CartaoVacina cartao = new CartaoVacina();

		Vacina vacina = vacinas.findOne(new Long(cartaoVacinaJson.getIdVacina()));
		cartao.setValor(vacina.getValor());
		cartao.setCartaoVacinaJson(cartaoVacinaJson, vacina);

		if (cartao.getDataAplicacao() != null) {
			cartao.setSituacao(StatusVacina.APLICADA);
		} else if (cartao.getDataRevacina().isAfter(LocalDate.now())) {
			cartao.setSituacao(StatusVacina.ATRASADA);
		} else {
			cartao.setSituacao(StatusVacina.PENDENTE);
		}

		cadastroCartaoVacinaService.salvar(cartao);

		mv = retornarModelCartaoVacinas(new Long(cartaoVacinaJson.getIdProntuario()));

		return mv;
	}

	private ModelAndView retornarModelCartaoVacinas(Long idProntuario) {
		Animal animal = animais.findByProntuarioId(idProntuario);

		ModelAndView mv = new ModelAndView("cartaoVacina/tabela-vacinacao");
		mv.addObject("cartaoVacinas", animal.getProntuario().getCartaoVacinas());
		return mv;
	}
}
