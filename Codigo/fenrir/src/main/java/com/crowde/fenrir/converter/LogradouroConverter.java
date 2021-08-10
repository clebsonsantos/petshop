package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Logradouro;

public class LogradouroConverter implements Converter<String, Logradouro> {

	@Override
	public Logradouro convert(String id) {
		Logradouro logradouro = null;
		if (!StringUtils.isEmpty(id)) {
			logradouro = new Logradouro();
			logradouro.setId(Long.valueOf(id));
		}
		return logradouro;

	}

}
