package com.gateway.api.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class ValidateService {
	
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claim = extractAllClaims(token);
		return claimResolver.apply(claim);
	}
	
	public Date extractExpirationDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpirationDate(token).before(new Date());
	}
	
	public boolean isTokenValid(String token) {
		return !isTokenExpired(token);
	}
}
