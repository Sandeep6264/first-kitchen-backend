package com.nt.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.requestDTO.JwtErrorResponseDTO;
import com.nt.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter{
	
	
	
	private final UserDetailsService userDetailsService;
	
	
	@Autowired
	private  JwtUtil jwtService;
	
	 private final ObjectMapper objectMapper;
	
	public JwtAuthFilter(UserDetailsService userDetailsService,ObjectMapper objectMapper) {
		this.userDetailsService=userDetailsService;
		this.objectMapper=objectMapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			System.out.println("DO FILTER INTERNAL EXECUTING: " + request.getServletPath());
			String authHeader=request.getHeader("Authorization");
			String token=null;
			String username=null;
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			    filterChain.doFilter(request, response);
			    return;
			}
			if(authHeader!=null && authHeader.startsWith("Bearer ")) {
				token=authHeader.substring(7);
				username=jwtService.getUsername(token);
			}if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails user=userDetailsService.loadUserByUsername(username);
				if(jwtService.validateToken(token,user)) {
					UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
				}
				
			}
			  filterChain.doFilter(request, response);
		
	}catch (ExpiredJwtException ex) {
        handleException(response, "Tok	en expired", 401);

    } catch (UnsupportedJwtException ex) {
        handleException(response, "Unsupported token", 401);

    } catch (MalformedJwtException ex) {
        handleException(response, "Malformed token", 401);

    } catch (SignatureException ex) {
        handleException(response, "Invalid token signature", 401);

    } catch (IllegalArgumentException ex) {
        handleException(response, "Token is missing or empty", 401);
    }
}
	 private String extractToken(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");

	        if (header != null && header.startsWith("Bearer ")) {
	            return header.substring(7);
	        }
	        return null;
	    }

	    private void handleException(HttpServletResponse response,
	                                 String message,
	                                 int status) throws IOException {

	        response.setStatus(status);
	        response.setContentType("application/json");

	        JwtErrorResponseDTO JwtErrorResponseDTO=new JwtErrorResponseDTO(
	        		status,"Unauthorized",message,"S",LocalDateTime.now());
	        response.getWriter().write(objectMapper.writeValueAsString(JwtErrorResponseDTO));
	    }
	    
	}
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) {
//		System.out.println(
//			    "JWT FILTER -> " + request.getMethod() + " " + request.getServletPath()
//			);
//		String path = request.getRequestURI(); // full path including context
//		System.out.println(path);
//	    String context = request.getContextPath(); // usually /FirstKitchen
//	    System.out.println(context);
//	    path = path.substring(context.length()); // remove context path
//	    String method = request.getMethod();
////	    String path = request.getServletPath();
//
//	    return "OPTIONS".equalsIgnoreCase(method)
//	            || path.equals("/auth/login")
//	            || path.equals("/auth/register");
//
//	}
	
	

