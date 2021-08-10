package com.crowde.fenrir.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.crowde.fenrir.config.JPAConfig;
import com.crowde.fenrir.config.SecurityConfig;
import com.crowde.fenrir.config.ServiceConfig;
import com.crowde.fenrir.config.WebConfig;

/**
 * 
 * @author Alan Passos configuração de inicialização do spring
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {

		

		return new Filter[] {  };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
