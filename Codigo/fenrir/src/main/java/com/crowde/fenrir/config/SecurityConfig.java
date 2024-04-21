package com.crowde.fenrir.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.crowde.fenrir.security.AppUserDetailsService;
import com.crowde.fenrir.security.Permissao;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")	
			.antMatchers("/imagens/**");
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()	
		
//			.antMatchers("/funcionarios/**").hasRole(Permissao.CADASTRAR_FUNCIONARIO)			
//			.antMatchers("/clientes/**").hasRole(Permissao.CADASTRAR_CLIENTE)
//			.antMatchers("/consultas/**").hasRole(Permissao.CADASTRAR_CONSULTA)
			//.antMatchers("/animais/**").hasRole(Permissao.CADASTRAR_ANIMAL)
			.anyRequest().authenticated()	
			
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/consultas")
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
		.exceptionHandling()		
			.accessDeniedPage("/403")
			.and()
		.sessionManagement()
			.invalidSessionUrl("/login")
			.and()
		.sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/login");				
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
