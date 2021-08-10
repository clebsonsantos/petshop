package com.crowde.fenrir.repository.helper.vacina;

import java.util.List;

import com.crowde.fenrir.model.Especie;
import com.crowde.fenrir.model.Vacina;

public interface VacinasQueries {
	public List<Vacina> vaciansObrigatorias(Especie especie);
	public List<Vacina> findAllByEspecie(Especie especie);
}
