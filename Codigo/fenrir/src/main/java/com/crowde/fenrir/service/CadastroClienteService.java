package com.crowde.fenrir.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Cliente;
import com.crowde.fenrir.model.Logradouro;
import com.crowde.fenrir.repository.Clientes;
import com.crowde.fenrir.repository.Logradouros;
import com.crowde.fenrir.service.exception.ImpossivelExluirEntidade;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;

	@Autowired
	private Logradouros logradouros;

	public List<Cliente> todos() {
		return clientes.findAll();
	}

	@Transactional
	public void salvar(Cliente cliente) {

		cliente.getEndereco().getLogradouro().setCep(tratarCep(cliente.getEndereco().getLogradouro().getCep()));

		if (cliente.isNovo()) {
			cliente.setDataCadastro(new Date());
			cliente.getEndereco().setDataCadastro(new Date());				
		}else {
			Cliente clienteAux =	clientes.findOne(cliente.getId());
			cliente.setDataCadastro(clienteAux.getDataCadastro());
			
			cliente.getEndereco().setDataCadastro(clienteAux.getEndereco().getDataCadastro());
		}

		Optional<Logradouro> logradouroOptional = logradouros.findByCep(cliente.getEndereco().getLogradouro().getCep());
		if (logradouroOptional.isPresent()) {
			cliente.getEndereco().setLogradouro(logradouroOptional.get());
			cliente.getEndereco().setCidade(logradouroOptional.get().getCidade());
		} else {
			cliente.getEndereco().getLogradouro().setCidade(cliente.getEndereco().getCidade());
		}
		clientes.save(cliente);
	}

	@Transactional
	public void excluir(Long id) {

		try {
			clientes.delete(id);
			clientes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExluirEntidade("Impossível apagar cliente. Já foi utilizado em alguma consulta.");
		}
	}

	private String tratarCep(String cep) {
		return cep.replace(".", "").replace("-", "");
	}
}
