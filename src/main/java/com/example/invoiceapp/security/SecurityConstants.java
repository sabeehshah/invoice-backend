package com.example.invoiceapp.security;

public class SecurityConstants {
	
	private SecurityConstants() {
		
	}
	
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 900_000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
}
