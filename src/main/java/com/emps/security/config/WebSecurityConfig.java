package com.emps.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // indica ao spring que essa é uma classe de configuração
@EnableWebSecurity // habilita que esta classe vai configurar o websecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplements userDetailsService;
	
	// Define as URLs que precisam de autenticação para ser acessadas
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Indica que os recursos indicados de o index podem ser acessados por qualquer um sem autenticação
		http.csrf().disable().authorizeRequests().antMatchers("/javax.faces.resource/**", "/index.xhtml").permitAll() //página pública
			.anyRequest().authenticated() //qualquer outro link necessita de autenticação			
			//login
			//qualquer um pode acessar a página de login
			.and().formLogin().loginPage("/login.xhtml").permitAll()
			//se houver falha no login, envia o parâmetro erro=true
			//.failureUrl("/login.xhtml?error=true")
			//caso ocorra tudo bem, redireciona para o link descrito
			.defaultSuccessUrl("/restrito/vendas/vendas.xhtml")
			
			//logout
	        .and().logout().logoutUrl("/logout") //Uri de requisição para logout
	        .logoutSuccessUrl("/login.xhtml");//página para onde será redirecionado quando a sessão for invalidada
	}

	// define como vai ser feita a autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// serve para que o spring security não bloqueie as páginas estáticas como
	// styles.css e etc
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/css/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}