package com.crowde.fenrir.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Cliente;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Cliente.class);		
		
		if(isPessoaSelecionada(cliente)) {
			if(!StringUtils.isEmpty(cliente.getCpfCnpj())) {
				grupos.add(cliente.getTipoCliente().getGrupo());			
			}
		}
		
		return grupos;
	}

	private boolean isPessoaSelecionada(Cliente cliente) {
		return cliente!=null && cliente.getTipoCliente()!=null;
	}

}
