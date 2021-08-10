package com.crowde.fenrir.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.crowde.fenrir.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.crowde.fenrir.thymeleaf.processor.MessageElementTagProcessor;

public class CrowdeDialect extends AbstractProcessorDialect {

	public CrowdeDialect() {
		super("CrowDe Software Fenrir", "crowde", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
