package com.crowde.fenrir.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.crowde.fenrir.model.json.CartaoVacinaJson;

@Entity
@Table(name = "cartao_vacina")
public class CartaoVacina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cartao_vacina")
	private Long id;

	private String observacao;
	
	private String dose;

	private BigDecimal valor;

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
	private StatusVacina situacao;

	@Column(name = "data_aplicacao")
	private LocalDateTime dataAplicacao;

	@Column(name = "data_revacina")
	private LocalDate dataRevacina;

	@ManyToOne
	@JoinColumn(name = "id_vacina")
	private Vacina vacina;

	@ManyToOne
	@JoinColumn(name = "id_prontuario")
	private Prontuario prontuario;
	
	@ManyToOne
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusVacina getSituacao() {
		return situacao;
	}

	public void setSituacao(StatusVacina situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDateTime dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public LocalDate getDataRevacina() {
		return dataRevacina;
	}

	public void setDataRevacina(LocalDate dataRevacina) {
		this.dataRevacina = dataRevacina;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public void setCartaoVacinaJson(CartaoVacinaJson cartaoVacinaJson, Vacina vacina) {
		this.vacina = vacina;
		if (cartaoVacinaJson.getDataRevacina() != null && !cartaoVacinaJson.getDataRevacina().equals("")) {
		this.setDataRevacina(
				LocalDate.parse(cartaoVacinaJson.getDataRevacina(), DateTimeFormatter.ofPattern("d/MM/yyyy")));
		}
		if (cartaoVacinaJson.getDataAplicacao() != null) {
			this.setDataAplicacao(LocalDateTime.parse(cartaoVacinaJson.getDataAplicacao(),
					DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm:ss")));
		}
		this.prontuario = new Prontuario();
		this.prontuario.setId(new Long(cartaoVacinaJson.getIdProntuario()));
		
		this.consulta = new Consulta();
		this.consulta.setId(new Long(cartaoVacinaJson.getIdConsulta()));		
		this.dose = cartaoVacinaJson.getDose();
	}

}
