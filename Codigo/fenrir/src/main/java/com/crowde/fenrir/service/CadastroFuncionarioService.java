package com.crowde.fenrir.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Funcionario;
import com.crowde.fenrir.model.Logradouro;
import com.crowde.fenrir.repository.Funcionarios;
import com.crowde.fenrir.repository.Logradouros;

@Service
public class CadastroFuncionarioService {

	@Autowired
	private Funcionarios funcionarios;

	@Autowired
	private Logradouros logradouros;

	public List<Funcionario> todos() {
		return funcionarios.findAll();
	}

	@Transactional
	public void salvar(Funcionario funcionario) {
		if (funcionario.isNovo()) {
			funcionario.setDataCadastro(new Date());

			funcionario.getEndereco().setDataCadastro(new Date());
		} else {
			Funcionario funcionarioAux = funcionarios.findOne(funcionario.getId());
			funcionario.setDataCadastro(funcionarioAux.getDataCadastro());
			funcionario.getEndereco().setDataCadastro(funcionarioAux.getEndereco().getDataCadastro());
		}

		funcionario.getEndereco().getLogradouro().setCep(tratarCep(funcionario.getEndereco().getLogradouro().getCep()));

		Optional<Logradouro> logradouroOptional = logradouros
				.findByCep(funcionario.getEndereco().getLogradouro().getCep());
		if (logradouroOptional.isPresent()) {
			funcionario.getEndereco().setLogradouro(logradouroOptional.get());
			funcionario.getEndereco().setCidade(logradouroOptional.get().getCidade());
		} else {
			funcionario.getEndereco().getLogradouro().setCidade(funcionario.getEndereco().getCidade());
		}

		String matricula = gerarMatricula(funcionario.getDataCadastro(), funcionario.getCpf());
		funcionario.setMatricula(matricula);

		funcionarios.save(funcionario);
	}

	private String gerarMatricula(Date dataCadastro, String cpf) {
		SimpleDateFormat format = new SimpleDateFormat("ddMMHHmmss");
		return format.format(dataCadastro) + cpf.substring(cpf.length() - 2, cpf.length());
	}

	private String tratarCep(String cep) {
		return cep.replace(".", "").replace("-", "");
	}
}
