package com.crowde.fenrir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Cor;

@Repository
public interface Cores extends JpaRepository<Cor, Long> {
	
	public Optional<Cor> findByDescricaoIgnoreCase(String descricao);
}
