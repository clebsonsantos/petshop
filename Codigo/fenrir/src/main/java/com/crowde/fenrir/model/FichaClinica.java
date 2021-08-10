package com.crowde.fenrir.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="ficha_clinica")
public class FichaClinica implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ficha_clinica")
	private Long id;
	
	@Column(name = "ficha")
	private String ficha;

	@Column(name = "diagnostico")
	private String diagnostico;

	@Column(name = "exames_solicitados")
	private String examesSolicitados; 
	
	@Column(name = "tratamento")
	private String tratamento;

	@NotNull(message = "É necessário informar se foi aplicada vermifugação")
	@Column(name = "vermifugacao")
	private boolean vermifugacao;
	
	@Column(name = "temperatura")
	private BigDecimal temperatura;
	
	@CreationTimestamp
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "data_retorno")
	private LocalDate dataRetorno;
	
	@ManyToOne
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;
	
	@ManyToOne
	@JoinColumn(name = "id_prontuario")
	private Prontuario prontuario;
	
	@Column(name = "ativa")
	private boolean ativa;

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(LocalDate dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consulta == null) ? 0 : consulta.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataRetorno == null) ? 0 : dataRetorno.hashCode());
		result = prime * result + ((diagnostico == null) ? 0 : diagnostico.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prontuario == null) ? 0 : prontuario.hashCode());
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
		FichaClinica other = (FichaClinica) obj;
		if (consulta == null) {
			if (other.consulta != null)
				return false;
		} else if (!consulta.equals(other.consulta))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataRetorno == null) {
			if (other.dataRetorno != null)
				return false;
		} else if (!dataRetorno.equals(other.dataRetorno))
			return false;
		if (diagnostico == null) {
			if (other.diagnostico != null)
				return false;
		} else if (!diagnostico.equals(other.diagnostico))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prontuario == null) {
			if (other.prontuario != null)
				return false;
		} else if (!prontuario.equals(other.prontuario))
			return false;
		return true;
	}

	public String getFicha() {
		return ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public String getExamesSolicitados() {
		return examesSolicitados;
	}

	public void setExamesSolicitados(String examesSolicitados) {
		this.examesSolicitados = examesSolicitados;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public boolean isVermifugacao() {
		return vermifugacao;
	}

	public void setVermifugacao(boolean vermifugacao) {
		this.vermifugacao = vermifugacao;
	}

	public BigDecimal getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(BigDecimal temperatura) {
		this.temperatura = temperatura;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	public boolean isNova( ) {
		return this.id == null;
	}
	
	public boolean isEditavel() {
		if (isNova()) {
			return true;
		} else if (this.consulta != null) {
			return this.consulta.getSituacao() == SituacaoConsulta.CONFIRMADO || 
				this.consulta.getSituacao() == SituacaoConsulta.ATENDIDO;
		} else {
			return false;
		}
	}

	
	
	
}
