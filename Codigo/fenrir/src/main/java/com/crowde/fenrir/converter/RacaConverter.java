package com.crowde.fenrir.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.crowde.fenrir.model.Raca;

public class RacaConverter implements Converter<String, Raca> {

	@Override
	public Raca convert(String id) {
		if(!StringUtils.isEmpty(id)) {
			Raca raca = new Raca();
			raca.setId(Long.valueOf(id));
			return raca;
		}
		
		return null;
	}

}
