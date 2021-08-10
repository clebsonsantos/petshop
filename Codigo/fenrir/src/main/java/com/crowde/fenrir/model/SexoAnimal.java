package com.crowde.fenrir.model;

public enum SexoAnimal {

	M("Macho"),
	F("Femea"),
	N("Não sabe");
	
	
	private String descricao;
	
	SexoAnimal(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
