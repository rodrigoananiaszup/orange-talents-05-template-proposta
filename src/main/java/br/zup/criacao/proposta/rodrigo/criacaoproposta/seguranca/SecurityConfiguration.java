package br.zup.criacao.proposta.rodrigo.criacaoproposta.seguranca;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(HttpMethod.GET, "/api/proposta/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.POST, "/api/proposta/**").hasAuthority("SCOPE_propostas")
				.anyRequest().authenticated())
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }


}