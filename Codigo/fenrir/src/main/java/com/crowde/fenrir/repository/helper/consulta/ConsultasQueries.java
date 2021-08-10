package com.crowde.fenrir.repository.helper.consulta;

import java.util.List;
import java.util.Optional;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.repository.filter.ConsultaFilter;

public interface ConsultasQueries {
	
	public Integer update(Consulta consulta);	
	public Integer alterarSituacao(Consulta consulta);
	public Integer finalizar(Consulta consulta);
	public Integer cancelar(Long id);
	
	
	public List<Consulta> ordenadas();
	public List<Consulta> pesquisaCliente(ConsultaFilter filtros);
		
}

