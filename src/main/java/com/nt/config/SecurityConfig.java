package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nt.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		private final JwtAuthFilter jwtAuthFilter;
		private final UserDetailsService userDetailsService;
		private final PasswordEncoder passwordEncoder;
		
		public SecurityConfig(JwtAuthFilter jwtAuthFilter,UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
			this.jwtAuthFilter=jwtAuthFilter;
			this.userDetailsService=userDetailsService;
			this.passwordEncoder=passwordEncoder;
		}
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.cors();
			http.csrf( csrf -> csrf.disable())
			.authorizeHttpRequests(auth  -> auth
					.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
					.requestMatchers("/auth/login","/auth/register").permitAll()
					.requestMatchers("/api/v1/payments/webhook").permitAll()
					.requestMatchers("/api/v1/payments/**").authenticated()
					.requestMatchers("/api/item/**","/api/order/**").hasAnyRole("USER","ADMIN")
					.requestMatchers("/auth/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					
					)
			
			.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
		}
		
		@Bean
		public AuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider  provider=new DaoAuthenticationProvider ();
			provider.setUserDetailsService(userDetailsService);
			provider.setPasswordEncoder(passwordEncoder);
			return provider;
		}
		
		
}
