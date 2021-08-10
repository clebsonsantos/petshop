package com.crowde.fenrir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowde.fenrir.model.Vacina;
import com.crowde.fenrir.repository.helper.vacina.VacinasQueries;

public interface Vacinas extends JpaRepository<Vacina, Long>, VacinasQueries {
	
}
