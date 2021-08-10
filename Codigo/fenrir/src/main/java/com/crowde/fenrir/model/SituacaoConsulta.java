package com.crowde.fenrir.model;

public enum SituacaoConsulta {

	PENDENTE("Pendente","label-info"),
	ATENDIDO("Atendido","label-primary"),
	CONFIRMADO("Confirmado","label-success"),
	CANCELADO("Cancelado","label-danger"),
	FINALIZADO("Finalizado","label-default");
	
	private String descricao;
	private String label;
	
	SituacaoConsulta(String descricao, String label){
		this.descricao = descricao;
		this.label = label;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getLabel() {
		return label;
	}
	
}
