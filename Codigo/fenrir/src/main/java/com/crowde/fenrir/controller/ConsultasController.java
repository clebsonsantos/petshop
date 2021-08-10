package com.crowde.fenrir.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowde.fenrir.controller.validator.ConsultaValidator;
import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.FormaPagamento;
import com.crowde.fenrir.model.SituacaoConsulta;
import com.crowde.fenrir.model.TipoFuncionario;
import com.crowde.fenrir.repository.Consultas;
import com.crowde.fenrir.repository.Funcionarios;
import com.crowde.fenrir.repository.Prontuarios;
import com.crowde.fenrir.repository.filter.ConsultaFilter;
import com.crowde.fenrir.service.CadastroConsultaService;
import com.crowde.fenrir.service.exception.ImpossivelExluirEntidade;


@Controller
@RequestMapping("/consultas")
public class ConsultasController{

	private final String CONSULTAR_CONSULTA = "consulta/consultar-consulta";
	private final String CADASTRAR_CONSULTA = "consulta/cadastro-consulta";
	private final String CONSULTA_PAGAMENTO = "consulta/consulta-pagamento";
	private final String COMPONENTE_PRONTUARIO = "cliente/componente-prontuario";
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private Consultas consultas;
	
	@Autowired
	private Prontuarios prontuarios;
	private ConsultaValidator val = new ConsultaValidator();
	
	private FormaPagamento formaPagamento;


	@Autowired
	private CadastroConsultaService cadastroConsultaService;


	@RequestMapping("/novo")
	public ModelAndView novo(Consulta consulta) {

		ModelAndView mv = new ModelAndView(CADASTRAR_CONSULTA);
		
		mv.addObject("situacoes", SituacaoConsulta.values());
		mv.addObject("formaPagamento", FormaPagamento.values());
		mv.addObject("funcionarios", funcionarios.findAllByTipoFuncionario(TipoFuncionario.VETERINARIO));

		return mv;
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Consulta consulta, BindingResult result, Model model,
			RedirectAttributes attributes) {
		
		val.validaCadastro(consulta, result);
		
	//	validarCampos(consulta, result);
		
		if(!consulta.isNova() || (!StringUtils.isEmpty(consulta.getProntuario()) && !StringUtils.isEmpty(consulta.getProntuario().getId()))) {
			consulta.setProntuario(prontuarios.findOne(consulta.getProntuario().getId()));
		}
				
		if (result.hasErrors()) {			
			return novo(consulta);
		}
	
		if (!consulta.isNova())  {
			attributes.addFlashAttribute("mensagem", "Consulta alterada com sucesso!");			
		}

		cadastroConsultaService.salvar(consulta);
		attributes.addFlashAttribute("mensagem", "Consulta salva com sucesso!");
		return new ModelAndView("redirect:/consultas");
	}

	private void validarCampos(Consulta consulta, BindingResult result) {
		if(StringUtils.isEmpty(consulta.getProntuario()) || StringUtils.isEmpty(consulta.getProntuario().getId())) {			
			result.rejectValue("prontuario","consulta.prontuario", "É necessário informar um o cliente");
		}
		
		if(StringUtils.isEmpty(consulta.getVeterinario()) || StringUtils.isEmpty(consulta.getVeterinario().getId())) {			
			result.rejectValue("veterinario","consulta.veterinario", "É necessário informar um veterinario");
		}
		
		if(StringUtils.isEmpty(consulta.getDataConsulta())) {
			result.rejectValue("dataConsulta","consulta.dataConsulta", "necessário informar a data");
		}
		
		if(StringUtils.isEmpty(consulta.getHoraConsulta())) {
			result.rejectValue("horaConsulta","consulta.horaConsulta", "necessário informar a hora");
		}
		
		if(StringUtils.isEmpty(consulta.getValor())) {
			result.rejectValue("valor","consulta.valor", "necessário informar o valor");
		}
		
		if (consulta.getIsExterna() && consulta.getValorAdicional().doubleValue() <= 0) 
			result.rejectValue("valorAdicional", "consulta.valorAdicional", "Consultas externas precisam ter o campo \"Valor Adicional\" maior que zero");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar (@PathVariable("id") Long id ) {
		Consulta con = consultas.findOne(id);
		ModelAndView mv = novo(con);
		mv.addObject(con);
		return mv;
	}
	
	
	
	@GetMapping("/pagamento/{id}")
	public ModelAndView pagamento (@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView(CONSULTA_PAGAMENTO);
		Consulta con = consultas.findOne(id);
		if (con.getValorPago() == null)
			con.setValorPago(BigDecimal.valueOf(0));
		
		if (con.getValorDesconto() == null)
			con.setValorDesconto(BigDecimal.valueOf(0));
		
		if (con.getValorAdicional() == null)
			con.setValorAdicional(BigDecimal.valueOf(0));
		
		mv.addObject("consulta", con);
		return mv;
	}
	
	
	@RequestMapping(value = {"/pagamento/{idConsulta:\\d+}"}, method = RequestMethod.POST)
	public ModelAndView efetuarPagamento (@Valid Consulta consulta, @PathVariable("idConsulta") Long idConsulta, BindingResult result, Model model,
			RedirectAttributes attributes) {
		
		if (result.hasErrors())
			return pagamento(consulta.getId());		
		
		
		cadastroConsultaService.finalizar(consulta);
		
		String url = "redirect:/consultas";
		
		attributes.addFlashAttribute("mensagem", "Pagamento efetuado com sucesso");
		return new ModelAndView(url);
	}
	
	
	@GetMapping("/cancelar/{idConsulta}")
	public ModelAndView cancelar(@PathVariable("idConsulta") Long idConsulta, RedirectAttributes attributes) {
		

		String url = "redirect:/consultas/";
	
		Consulta con = new Consulta(idConsulta);
		con.setSituacao(SituacaoConsulta.CANCELADO);
		
		cadastroConsultaService.salvar(con);
		
		attributes.addFlashAttribute("mensagem", "Consulta foi cancelada com sucesso");
		return new ModelAndView(url);
	}	

	@RequestMapping(value ="/prontuarios" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView prontuarios(
			@RequestParam(name = "id", defaultValue = "-1") Long id) {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}	
		ModelAndView mv = new ModelAndView(COMPONENTE_PRONTUARIO);
		List<Consulta> consultaList = consultas.findByProntuarioId(id);
		mv.addObject("consultas", consultaList);
		return mv;
	}


	
	@GetMapping
	public ModelAndView pesquisaConsulta( ConsultaFilter consultaFilter, BindingResult result) {

		List<Consulta> consultasLista = consultas.pesquisaCliente(consultaFilter);
		ModelAndView mv = new ModelAndView(CONSULTAR_CONSULTA);
		mv.addObject("consultas", consultasLista);
		return mv;
		
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroConsultaService.cancelar(id);
		} catch (ImpossivelExluirEntidade e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}


}
