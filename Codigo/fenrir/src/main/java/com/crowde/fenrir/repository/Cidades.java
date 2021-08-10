package com.crowde.fenrir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowde.fenrir.model.Cidade;

public interface Cidades extends JpaRepository<Cidade, Long> {
	public List<Cidade> findByEstadoId(Long idEstado);
}
