package com.crowde.fenrir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Endereco;

@Repository
public interface Enderecos extends JpaRepository<Endereco, Long> {	

}
