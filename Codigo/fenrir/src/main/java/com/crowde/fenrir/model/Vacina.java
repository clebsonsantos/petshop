package com.crowde.fenrir.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vacina")
public class Vacina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vacina")
	private Long id;
	
	private String descricao;	
		
	private BigDecimal valor;
	

	@Column(name = "data_cadastro")
	private Date dataCadastro;	
	
	/**
	 * joinColumns = tem que ser o id que esta na tabela do objeto atual
	 * inverseJoinColumns = tem que ser o id que esta na tabela cujo id e o especie
	 */	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "especie_vacina", joinColumns = @JoinColumn(name = "id_vacina"), inverseJoinColumns = @JoinColumn(name = "id_especie"))
	private List<Especie> especies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}
	
	

}
