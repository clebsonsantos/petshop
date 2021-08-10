package com.crowde.fenrir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Funcionario;
import com.crowde.fenrir.model.TipoFuncionario;

@Repository
public interface Funcionarios extends JpaRepository<Funcionario, Long>{

	public Funcionario findByMatricula(String matricula);
	public List<Funcionario> findAllByTipoFuncionario(TipoFuncionario tipoFuncionario);
	
}
