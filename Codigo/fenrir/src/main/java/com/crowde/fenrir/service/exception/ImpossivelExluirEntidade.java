package com.crowde.fenrir.service.exception;

public class ImpossivelExluirEntidade extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImpossivelExluirEntidade(String mensagem) {
		super(mensagem);
	}

}
