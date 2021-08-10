package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Prontuario;

public class ProntuarioConverter implements Converter<String, Prontuario> {

	@Override
	public Prontuario convert(String id) {
		Prontuario pront = null;

		if (!StringUtils.isEmpty(id)) {
			pront = new Prontuario();
			pront.setId(Long.valueOf(id));
		}
		return pront;

	}

}
