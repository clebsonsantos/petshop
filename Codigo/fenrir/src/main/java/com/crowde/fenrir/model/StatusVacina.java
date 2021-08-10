package com.crowde.fenrir.model;

public enum StatusVacina {

	APLICADA("Aplicada", "label-default"),
	PENDENTE("Pendente","label-warning"),
	ATRASADA("Atrasada","label-danger");

	
	private String descricao;
	private String label;
	
	StatusVacina(String descricao, String label){
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
