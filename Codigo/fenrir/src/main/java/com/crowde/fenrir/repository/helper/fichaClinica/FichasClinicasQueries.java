package com.crowde.fenrir.repository.helper.fichaClinica;

import com.crowde.fenrir.model.Consulta;
import com.crowde.fenrir.model.FichaClinica;

public interface FichasClinicasQueries {
	
	public void desativarFichas(Consulta consulta);
	public FichaClinica findFichaByConsulta(Consulta consulta);

}
