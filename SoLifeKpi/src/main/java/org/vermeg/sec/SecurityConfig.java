package org.vermeg.sec;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//comment il va chercher les utilisateur et les roles il va faire appel a userDetails qui est deja reimplementer
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
        auth
        .ldapAuthentication()
        .userDnPatterns("uid={0},ou=users")
        .groupSearchBase("ou=users")
        .contextSource(contextSource())
        .passwordCompare()
        .passwordEncoder(new LdapShaPasswordEncoder())
        .passwordAttribute("userPassword");
		
	}
	
	//definir les droit d'accees et les filtre
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();//on va pas utiliser les session ; disable pour desactiver l'authentification par sesson
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //desactiver la session	
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		http.authorizeRequests().antMatchers("/codeevolution/Projectevolution/**").permitAll();

		http.authorizeRequests().anyRequest().authenticated();//tout les autre nessesite une authentif sans role
		http.addFilter(new JWTAuthentificationFilter(authenticationManager()));
		//le premier filtre qui intercepte les requetes
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:10389/"), "dc=vermeg,dc=com");
    }
}
