package com.crowde.fenrir.repository.helper.vacina;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.crowde.fenrir.model.Especie;
import com.crowde.fenrir.model.Vacina;

public class VacinasImpl implements VacinasQueries {

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Vacina> vaciansObrigatorias(Especie especie) {
		
		return manager.createQuery("select v from Vacina v inner join v.especies e where e = :especie and v.obrigatoria = true", Vacina.class)
				.setParameter("especie",especie).getResultList();
	}

	@Override
	public List<Vacina> findAllByEspecie(Especie especie) {
		return manager.createQuery("select v from Vacina v "
				+ "inner join v.especies e where e = :especie", Vacina.class )
				.setParameter("especie", especie)
				.getResultList();
	}
	
	

}
