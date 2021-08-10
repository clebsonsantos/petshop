package com.crowde.fenrir.repository.helper.fichaClinica;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.FichaClinica;

public class FichasClinicasImpl implements FichasClinicasQueries{

	@Autowired
	private EntityManager entityManager;


	
	@Override
	public void desativarFichas(Consulta consulta) {
		
		entityManager.createQuery("UPDATE FichaClinica fc SET fc.ativa = false "
				+ "WHERE fc.consulta = :consulta")
		.setParameter("consulta", consulta).executeUpdate();
		
	}
	
	@Override
	public FichaClinica findFichaByConsulta(Consulta consulta) {
		
		try {
		FichaClinica result = entityManager.createQuery("select fc from FichaClinica fc "
														+ "inner join fc.consulta c "
														+ "where c.id = :idConsulta "
														+ "and fc.ativa = true", FichaClinica.class)
								.setParameter("idConsulta", consulta.getId())
								.getSingleResult();
		

		return result;
		}catch (NoResultException e) {
			return new FichaClinica();
		}
	}
}

