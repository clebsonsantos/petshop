package com.crowde.fenrir.repository.helper.consulta;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.SituacaoConsulta;
import com.crowde.fenrir.repository.filter.ConsultaFilter;

public class ConsultasImpl implements ConsultasQueries {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Consulta> ordenadas() {

		return entityManager.createQuery("from Consulta "
				+ "order by FIELD (situacao, 'CONFIRMADO', 'PENDENTE', 'CANCELADO', 'FINALIZADO') "
				+ ", data_consulta, hora_consulta, id_veterinario, id_prontuario",
				Consulta.class).getResultList();
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Consulta> pesquisaCliente(ConsultaFilter filtro) {
		StringBuilder query = new StringBuilder("select con from Consulta con ");
		
		if (!StringUtils.isEmpty(filtro.getClienteNome())) {
			
			query.append("inner join con.prontuario as p " +
					"inner join p.animal as a " +
					"inner join a.cliente as c " +
					"where lower(c.nome) like lower(:nome) ");
			
			if (!StringUtils.isEmpty(filtro.getDataInicio()) && !StringUtils.isEmpty(filtro.getDataFim()))
				query.append("and con.dataConsulta between :dataInicio and :dataFim");
		} else if (!StringUtils.isEmpty(filtro.getDataInicio()) && !StringUtils.isEmpty(filtro.getDataFim())) {
			query.append("where con.dataConsulta between :dataInicio and :dataFim");
		}
		
		query.append(" order by "
				+ "CASE WHEN situacao='CONFIRMADO' THEN 1 "
				+ "WHEN situacao='PENDENTE' THEN 2 "
				+ "WHEN situacao='ATENDIDO' THEN 3 "
				+ "WHEN situacao='CANCELADO' THEN 4 "
				+ "WHEN situacao='FINALIZADO' THEN 5 END, "
				+ " con.dataConsulta desc, con.horaConsulta, con.veterinario, con.prontuario");
		
		Query result = entityManager.createQuery(query.toString(), Consulta.class);
		
		if (!StringUtils.isEmpty(filtro.getClienteNome()))
			result.setParameter("nome", "%"+filtro.getClienteNome()+"%");
		
		if (!StringUtils.isEmpty(filtro.getDataInicio()) && !StringUtils.isEmpty(filtro.getDataFim())) {
			result.setParameter("dataInicio", filtro.getDataInicio());
			result.setParameter("dataFim", filtro.getDataFim());
		}
		
		return result.getResultList();
	}

	@Override
	public Integer update(Consulta consulta) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" UPDATE Consulta c SET " + " c.dataConsulta = :dataConsulta "
				+ " ,c.horaConsulta=:horaConsulta "
				+ " ,c.valor = :valor "
				+ " ,c.valorAdicional = :valorAdicional "
				+ " ,c.situacao = :situacao "
				+ " ,c.isRetorno = :retorno "
				+ " ,c.isExterna = :externa "
				+ " ,c.atendente = :atendente "
				+ " ,c.veterinario = :veterinario");
		
		
		switch (consulta.getSituacao()) {
		case CONFIRMADO:
			hql.append(" ,c.dataConfirmacao = now() ");
			break;
		case CANCELADO:
			hql.append(" ,c.dataCancelamento = now() ");
			break;
		case FINALIZADO:
			hql.append(" ,c.dataFinalizacao = now() ");
			break;
		case ATENDIDO:
			hql.append(" ,c.dataAtendimento = now() ");
		default:
			break;	
		}
		
		hql.append(" where c.id = :id");	
		
		Query query = entityManager.createQuery(hql.toString());
		
		query.setParameter("dataConsulta", consulta.getDataConsulta())
		.setParameter("horaConsulta", consulta.getHoraConsulta())
		.setParameter("valor", consulta.getValor())
		.setParameter("valorAdicional", consulta.getValorAdicional())
		.setParameter("situacao", consulta.getSituacao())
		.setParameter("externa", consulta.getIsExterna())
		.setParameter("retorno", consulta.getIsRetorno())
		.setParameter("atendente", consulta.getAtendente())
		.setParameter("veterinario", consulta.getVeterinario())
		.setParameter("id", consulta.getId());	
		
		
		return query.executeUpdate();
		
	}

	@Override
	public Integer alterarSituacao(Consulta consulta) {
		StringBuilder hql = new StringBuilder();
		hql.append(" UPDATE Consulta c SET c.situacao = :situacao " );
		
		
		switch (consulta.getSituacao()) {
		case CONFIRMADO:
			hql.append(" ,c.dataConfirmacao = now() ");
			break;
		case CANCELADO:
			hql.append(" ,c.dataCancelamento = now() ");
			break;
		case FINALIZADO:
			hql.append(" ,c.dataFinalizacao = now() ");
			break;
		case ATENDIDO:
			hql.append(" ,c.dataAtendimento = now() ");
			break;
		default:
			break;	
		}
		
		hql.append(" where c.id = :id");	
		
		Query query = entityManager.createQuery(hql.toString());
		
		query.setParameter("sitaucao", consulta.getSituacao())
		.setParameter("id", consulta.getId());	
		
		
		return query.executeUpdate();
		
	}

	@Override
	public Integer finalizar(Consulta consulta) {
		StringBuilder hql = new StringBuilder();
		hql.append(" UPDATE Consulta c SET c.situacao = :situacao "
				+ " , c.dataFinalizacao = now() "								
				+ " , c.valorPago = :valorPago "
				+ " , c.valorDesconto = :valorDesconto ");
		
		
		
		if (consulta.isPagamentoValido()) {
			hql.append(" , c.dataPagamento = :dataPagamento, c.isPago = :pago ");
		}
		
		hql.append(" where c.id = :id ");
		Query query = entityManager.createQuery(hql.toString());
		
		if (consulta.isPagamentoValido()) {		
			query.setParameter("pago", true);
			query.setParameter("dataPagamento", LocalDateTime.now());
		}
		query.setParameter("situacao", SituacaoConsulta.FINALIZADO);
		query.setParameter("valorPago", consulta.getValorPago());
		query.setParameter("valorDesconto", consulta.getValorDesconto());
		query.setParameter("id", consulta.getId());
		
		
		return query.executeUpdate();
	}

	@Override
	public Integer cancelar(Long id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" UPDATE Consulta c SET c.situacao = :situacao, c.dataCancelamento = now() where c.id = :id");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("situacao", SituacaoConsulta.CANCELADO);
		query.setParameter("id", id);
		
		
		return query.executeUpdate();
	}
}
