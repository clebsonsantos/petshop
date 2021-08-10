package com.crowde.fenrir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.crowde.fenrir.service.CadastroClienteService;
import com.crowde.fenrir.storage.FotoStorage;
import com.crowde.fenrir.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroClienteService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	

}
