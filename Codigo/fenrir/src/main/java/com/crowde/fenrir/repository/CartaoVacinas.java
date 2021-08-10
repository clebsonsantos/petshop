package com.crowde.fenrir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.CartaoVacina;

@Repository
public interface CartaoVacinas extends JpaRepository<CartaoVacina, Long> {	
	
	
}
