package com.crowde.fenrir.model;

public enum SexoPessoa {

	M("Masculino"),
	F("Feminino");	
	
	
	private String descricao;
	
	SexoPessoa(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
