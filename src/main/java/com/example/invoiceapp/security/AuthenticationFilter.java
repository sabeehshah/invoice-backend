package com.example.invoiceapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.invoiceapp.model.User;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub

		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(Feature.AUTO_CLOSE_SOURCE,true);
			
			User creds = objectMapper.readValue(request.getInputStream(), User.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("Could not read request" + e);
		} catch(DisabledException e) {
			throw new DisabledException(SPRING_SECURITY_FORM_USERNAME_KEY);
		} catch(LockedException e) {
			throw new LockedException(SPRING_SECURITY_FORM_USERNAME_KEY);
		} catch(BadCredentialsException e) {
			throw new BadCredentialsException(SPRING_SECURITY_FORM_USERNAME_KEY+SPRING_SECURITY_FORM_PASSWORD_KEY);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String token = Jwts.builder().setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + 120000))
				.signWith(SignatureAlgorithm.HS512, "SecretKeyToGenJWTs".getBytes()).compact();
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
		response.addHeader("Authorization", "Bearer " + token);
		
		
	}
	
	

}
