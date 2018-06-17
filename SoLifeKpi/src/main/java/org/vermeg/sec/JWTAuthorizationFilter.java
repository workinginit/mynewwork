package org.vermeg.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterchain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", 
				"Origin, Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, "
				+ "Access-Control-Request-Headers,"
				+ "Authorization");
		response.addHeader("Access-Control-Expose-Headers", 
				"Access-Control-Allow-Origin,"
				+ "Access-Control-Allow-Credentials,"
				+ "Authorization");
	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS");
		
		//get authorization (JWT)
		String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
		
		System.out.println("JWT Angu: "+jwt);
		
		if(request.getMethod().equals("OPTIONS")) {
			//la reponse va etre avec les response.addHeader
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			
			if(jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterchain.doFilter(request, response);
				return;
			}
			
			Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replaceFirst(SecurityConstants.TOKEN_PREFIX, ""))//on supprime le prefix
				.getBody();
			
			String username = claims.getSubject();
			ArrayList<Map<String,String>> roles = (ArrayList<Map<String,String>>) claims.get("roles");
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			
		
			
			//l'authentification est deja faite vu qu'on a le jwt
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			//je passe au filter suivant
			filterchain.doFilter(request, response);
		}
	
	}

}
