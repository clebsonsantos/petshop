package com.crowde.fenrir.model;

public enum TipoFuncionario {

	VETERINARIO("Veterinário"),
	ATENDENTE("Atendente");
	
	private String descricao;
	
	private TipoFuncionario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
