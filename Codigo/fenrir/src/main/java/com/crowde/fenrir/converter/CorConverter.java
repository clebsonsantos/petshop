package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Cor;

public class CorConverter implements Converter<String, Cor> {

	@Override
	public Cor convert(String id) {

		Cor cor = null;
		if (!StringUtils.isEmpty(id)) {
			cor = new Cor();
			cor.setId(Long.valueOf(id));
		}
		return cor;
	}

}
