package com.crowde.fenrir.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.repository.helper.consulta.ConsultasQueries;


@Repository
@Transactional(readOnly = true)
public interface Consultas extends JpaRepository<Consulta, Long>, ConsultasQueries{
	public List<Consulta> findByProntuarioId(Long id);
}
