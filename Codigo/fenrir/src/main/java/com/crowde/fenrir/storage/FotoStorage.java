package com.crowde.fenrir.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoRemporaria(String nome);

	public void salvar(String imagem);

	public byte[] recuperar(String nome);
}
