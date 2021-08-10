package com.crowde.fenrir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crowde.fenrir.model.Raca;

@Repository
public interface Racas extends JpaRepository<Raca, Long>{

	public List<Raca> findByEspecieId(Long idEspecie);
}
