package com.crowde.fenrir.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prontuario")
public class Prontuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prontuario")
	private Long id;

	private String matricula;

	private String observacao;

	@Column(name = "data_ultima_edicao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaEdicao;

	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@JsonIgnore
	@OneToOne(mappedBy="prontuario")
	private Animal animal;

	@OneToMany(mappedBy = "prontuario", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonIgnore
	private List<CartaoVacina> cartaoVacinas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataUltimaEdicao() {
		return dataUltimaEdicao;
	}

	public void setDataUltimaEdicao(Date dataUltimaEdicao) {
		this.dataUltimaEdicao = dataUltimaEdicao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<CartaoVacina> getCartaoVacinas() {
		return cartaoVacinas;
	}

	public void setCartaoVacinas(List<CartaoVacina> cartaoVacinas) {
		this.cartaoVacinas = cartaoVacinas;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prontuario other = (Prontuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
