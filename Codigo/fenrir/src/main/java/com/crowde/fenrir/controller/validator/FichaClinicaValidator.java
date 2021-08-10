package com.crowde.fenrir.controller.validator;

import java.util.ArrayList;

import com.crowde.fenrir.model.FichaClinica;

import Architecture.ControllerValidator;
import Architecture.ValidatorMessage;

public class FichaClinicaValidator extends ControllerValidator {
	
	public FichaClinicaValidator() {
		this.messages = new ArrayList<ValidatorMessage>();
		this.obj = new FichaClinica();
		
	}
	
	@Override
	public void regrasCadastro(Object obj) {
		FichaClinica ficha = (FichaClinica) obj;
		if (isNull(ficha))
			addMessage("ficha", "Objeto informado é nulo" );
		if (isNull(ficha.getTemperatura()) || ficha.getTemperatura().doubleValue() < 0) 
			addMessage("temperatura", "É necessário informar a temperatura do animal");
		if (isNull(ficha.getFicha()))
			addMessage("ficha", "É necenssário preencher o campo de Dados Gerais com informações");
		if (isNull(ficha.getDiagnostico()))
			addMessage("diagnostico", "É necessário preencher o campo de Diagnóstico com informações");
		if (!isNull(ficha.getDataRetorno()))
			if(!isDateTodayOrFuture(ficha.getDataRetorno()))
				addMessage("dataRetorno", "É necessário informar uma data de retorno válida igual ou maior que hoje");		
		
	}

}
