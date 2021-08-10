package com.crowde.fenrir.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@CPF
	@NotBlank(message = "O CPF é obrigatório")
	private String cpf;
	
	private String especialidade;

	private String crmv;

	private String matricula;

	@Column(name = "data_admissao")
	@NotNull(message = "Data de admissão é obrigatório")
	private LocalDate dataAdmissao;

	@NotNull(message="O salário é obrigatório")
	@Min(value=100, message="O saláro tem que ser maior que R$ 100,00")
	private BigDecimal salario;
	
	@OneToOne( mappedBy="funcionario")	
	@JsonIgnore
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_funcionario")
	private TipoFuncionario tipoFuncionario;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getCrmv() {
		return crmv;
	}

	public void setCrmv(String crmv) {
		this.crmv = crmv;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	
	public boolean isNovo() {
		return getId() == null;
	}

}
