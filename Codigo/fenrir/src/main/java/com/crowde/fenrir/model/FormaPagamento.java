package com.crowde.fenrir.model;

public enum FormaPagamento {

	
	CARTAO("Cartão"),
	DINHEIRO("Dinheiro"),
	CHEQUE("Cheque"),
	FICHA("Ficha");
	
	private String descricao;

	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}	
	
	
}
