package org.vermeg.sec;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vermeg.entities.AppUser;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter  {
	
	private AuthenticationManager authenticationManager;

	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	//1ere methode : on recupere les donnee
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	AppUser appUser = null;

	try {
		appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
	} catch (IOException e) {
		throw new RuntimeException(e);
	}

		System.out.println("user one : "+appUser.getUsername());
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
	}
	
	//2eme methode
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		LdapUserDetailsImpl springUser = (LdapUserDetailsImpl) authResult.getPrincipal();
		System.out.println("dd"+springUser.getDn()+springUser.getClass().getName());
		//creation d'un nouveau objet JWT
		String jwt = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", null)
				.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);

	}

}
