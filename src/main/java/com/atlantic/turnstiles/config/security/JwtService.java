package com.atlantic.turnstiles.config.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${security.jwt.secret-key}")
	private String secretKey;

	@Value("${security.jwt.expiration-time}")
	private Long jwtExpiration;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	public String generateToken(UserDetails user) {
		return generateToken(new HashMap<>(), user);
	}

	public String generateToken(Map<String, Object> extractClaims, UserDetails user) {
		return  buildToken(extractClaims, user, jwtExpiration);
	}
	
	public boolean isTokenValid(String token, UserDetails user) {
		final String username = extractUsername(token);
		return (username.equals(user.getUsername()) && isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private String buildToken(Map<String, Object> extraClaims, UserDetails user, long expiration) {
		return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (expiration * 24 * 60 * 60 * 1000)))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public long getExpirationTime() {
		return jwtExpiration;
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJwt(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keysBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keysBytes);
	}
}
