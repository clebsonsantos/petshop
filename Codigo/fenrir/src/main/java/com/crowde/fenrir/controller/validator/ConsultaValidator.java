package com.crowde.fenrir.controller.validator;

import java.util.ArrayList;

import org.springframework.validation.BindingResult;

import com.crowde.fenrir.model.Consulta;

import Architecture.GenericValidator;
import Architecture.ValidatorMessage;

public class ConsultaValidator extends GenericValidator {
	
	
	public ConsultaValidator() {
		this.messages = new ArrayList<ValidatorMessage>();
		this.obj = new Consulta();
	}
	
	public boolean validaCadastro(Consulta consulta, BindingResult result) {
		this.messages.clear();
		
		if (isNull(consulta)) {
			addMessage("consulta", "Objeto informado é nulo");
			return false;
		}
		if (isNull(consulta.getProntuario()) || isNull(consulta.getProntuario().getId()))
			addMessage("prontuario", "É necessário informar um cliente.");
		if (isNull(consulta.getVeterinario()) || isNull(consulta.getVeterinario().getId())) 
			addMessage("veterinario", "É necessário informar um veterinário.");
		if (!isDateTodayOrFuture(consulta.getDataConsulta()))
			addMessage("dataConsulta", "É necessário informar uma data válida igual ou maior que hoje.");
		if (isNull(consulta.getHoraConsulta())) 
			addMessage("horaConsulta", "É necessário informar uma hora válida.");
		if (isNull(consulta.getValor()) || consulta.getValor().doubleValue() < 0)
			addMessage("valor", "É necessário informar um valor para a consulta maior que zero");
		if (!isNull(consulta.getIsExterna()) && consulta.getIsExterna())
				if (isNull(consulta.getValorAdicional()) || consulta.getValorAdicional().doubleValue() <= 0)
					addMessage("valorAdicional", "Consultas externas precisam ter o campo \"Valor Adicional\" maior que zero");
					
		if (!messages.isEmpty()) {
			populateResult(result);
			return false;
		}
		else 
			return true;
	}

}
