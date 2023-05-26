package com.service.authentication.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		System.out.println("Jwt Generate Token : " + claims);
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		System.out.println("Create Token 33");
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

//	public Claims extractAllClaims(String token) {
//		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
//	}
//
//	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//		final Claims claim = extractAllClaims(token);
//		return claimResolver.apply(claim);
//	}
//	
//	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//	
//	public Date extractExpirationDate(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//	
//	public boolean isTokenExpired(String token) {
//		return extractExpirationDate(token).before(new Date());
//	}
//	
//	public boolean isTokenValid(String token, String username) {
//		return !isTokenExpired(token) && username.equals(extractUsername(token));
//	}

}
