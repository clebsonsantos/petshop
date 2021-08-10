package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Cidade;

public class CidadeConverter implements Converter<String, Cidade> {

	@Override
	public Cidade convert(String id) {
		Cidade cidade = null;
		if (!StringUtils.isEmpty(id)) {
			cidade = new Cidade();
			cidade.setId(Long.valueOf(id));
		}
		return cidade;

	}

}
