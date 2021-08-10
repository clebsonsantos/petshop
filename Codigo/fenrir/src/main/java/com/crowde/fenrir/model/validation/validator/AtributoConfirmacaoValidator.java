package com.crowde.fenrir.model.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.crowde.fenrir.model.validation.AtributoConfirmacao;

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributoConfirmacao, Object> {

	private String atributo;
	private String atributoConfirmacao;

	@Override
	public void initialize(AtributoConfirmacao atributoConfirmacao) {
		this.atributo = atributoConfirmacao.atributo();
		this.atributoConfirmacao = atributoConfirmacao.atributoConfirmacao();

	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
		boolean valido = false;

		try {
			Object valorAtributo = BeanUtils.getProperty(object, this.atributo);
			Object valorAtributoConfirmacao = BeanUtils.getProperty(object, this.atributoConfirmacao);
			valido = ambosSaoNull(valorAtributo, valorAtributoConfirmacao)
					|| amboesSaoIguais(valorAtributo, valorAtributoConfirmacao);

		} catch (Exception e) {
			throw new RuntimeException("Erro recuperando valores dos atributos", e);
		}
		
		if(!valido) {
			constraintValidatorContext.disableDefaultConstraintViolation();
			String messagem = constraintValidatorContext.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = constraintValidatorContext.buildConstraintViolationWithTemplate(messagem);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
			
		}
		
		return valido;
	}

	private boolean amboesSaoIguais(Object valorAtributo, Object valorAtributoConfirmacao) {

		return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
	}

	private boolean ambosSaoNull(Object valorAtributo, Object valorAtributoConfirmacao) {

		return valorAtributo == null && valorAtributoConfirmacao == null;
	}

}
