package com.crowde.fenrir.repository;

import com.crowde.fenrir.model.FichaClinica;
import com.crowde.fenrir.repository.helper.fichaClinica.FichasClinicasQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface FichasClinicas extends JpaRepository<FichaClinica, Long>, FichasClinicasQueries{
	
	

}
