package com.crowde.fenrir.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowde.fenrir.model.Prontuario;

@Repository
@Transactional(readOnly = true)
public interface Prontuarios extends JpaRepository<Prontuario, Long> {

}
