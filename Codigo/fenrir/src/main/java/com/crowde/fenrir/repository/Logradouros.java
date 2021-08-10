package com.crowde.fenrir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowde.fenrir.model.Logradouro;

public interface Logradouros extends JpaRepository<Logradouro, Long> {

	public Optional<Logradouro> findByCep(String cep);

}
