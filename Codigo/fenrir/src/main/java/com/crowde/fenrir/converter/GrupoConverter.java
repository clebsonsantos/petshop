package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String id) {
		Grupo grupo = null;
		if (!StringUtils.isEmpty(id)) {
			grupo = new Grupo();
			grupo.setId(Long.valueOf(id));
		}
		return grupo;

	}

}
