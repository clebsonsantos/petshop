package com.crowde.fenrir.repository.helper.cliente;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Cliente;
import com.crowde.fenrir.repository.filter.ClienteFilter;

public class ClientesImpl implements ClientesQueries {

	@Autowired
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		criteria.setFetchMode("animais",FetchMode.SELECT);
		adicionarFiltro(filtro, criteria);

		List<Cliente> clientes = criteria.list();

		return new PageImpl<>(clientes, pageable, total(filtro));
	}

	private Long total(ClienteFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);
		 criteria.setProjection(Projections.rowCount());
		 criteria.setFetchMode("animais",FetchMode.SELECT);
		adicionarFiltro(filtro, criteria);
		return (Long) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
	}

	private void adicionarFiltro(ClienteFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCpfCnpj())) {
				criteria.add(Restrictions.eq("cpfCnpj", filtro.getCpfCnpj()));
			}
			if (!StringUtils.isEmpty(filtro.getRg())) {
				criteria.add(Restrictions.eq("rg", filtro.getRg()));
			}
			if (!StringUtils.isEmpty(filtro.getTelefone())) {
				criteria.add(Restrictions.eq("telefoneCelular", filtro.getTelefone()));
			}
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filtro.getEmail())) {
				criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
			}
		}
	}

}
