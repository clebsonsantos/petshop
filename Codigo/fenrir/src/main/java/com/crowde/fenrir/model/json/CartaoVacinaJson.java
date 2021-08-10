package com.crowde.fenrir.model.json;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class CartaoVacinaJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Vacina campo obrigatório")
	private String idVacina;	
	
	private String idConsulta;
	
	private String idProntuario;
	
	private String dataRevacina;
	
	private String dataAplicacao;
	
	private String observacao;
	
	@NotBlank(message="Dose é obrigatório")
	private String dose;

	public String getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(String idVacina) {
		this.idVacina = idVacina;
	}	
	
	public String getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(String idProntuario) {
		this.idProntuario = idProntuario;
	}

	public String getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getDataRevacina() {
		return dataRevacina;
	}

	public void setDataRevacina(String dataRevacina) {
		this.dataRevacina = dataRevacina;
	}

	public String getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(String dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
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
	
}
