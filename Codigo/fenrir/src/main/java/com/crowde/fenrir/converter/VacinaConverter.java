package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Vacina;

public class VacinaConverter implements Converter<String, Vacina> {

	@Override
	public Vacina convert(String id) {
		Vacina vacina = null;
		if (!StringUtils.isEmpty(id)) {
			vacina = new Vacina();
			vacina.setId(Long.valueOf(id));
		}
		return vacina;

	}

}
