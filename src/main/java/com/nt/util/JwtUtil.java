package com.nt.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	        @Value("${jwt.secret}")
	        private String secret;
			private   Key key;
			
			 @PostConstruct
			    public void init() {
			        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
			    }
			 private  final long EXPIRATION = 1000 * 60 * 60; 
			 
			 public  String generateToken(String subject) {
				 long now=System.currentTimeMillis();
				 return Jwts.builder()
						 .setSubject(subject)
						 .setIssuer("VKT")
						 .setIssuedAt(new Date(now))
						 .setExpiration(new Date(now+EXPIRATION))
						 .signWith(key,SignatureAlgorithm.HS512)
						 .compact();
			 }
			 
			 public  String getUsername(String token) {
				 return getClaims(token).getSubject();
			 }
			 
			 public  Boolean isExpired(String token) {
				 return getClaims(token).getExpiration().before(new Date());
			 }
			 
			 public  Claims getClaims(String token) {
				 return Jwts.parser()
						 .setSigningKey(key)
						 .build()
						 .parseClaimsJws(token)
						 .getBody();
			 }
			 public Boolean validateToken(String token, UserDetails userDetails) {
			        final String username = getUsername(token);
			        return (username.equals(userDetails.getUsername()) && !isExpired(token));
			    }

}
