package com.regesta.exercise.regestamarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.regesta.exercise.regestamarket.security.JWTAuthenticationProvider;
import com.regesta.exercise.regestamarket.security.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				
        http.cors().and().csrf().disable();
		
        return http.build();
        
    }
	
//	@Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(provider());
//        return authenticationManagerBuilder.build();
//    }
	 
	public JWTFilter jwtFilter() {
		return new JWTFilter();
	}
	
	@Bean
	public JWTAuthenticationProvider provider() {
	    return new JWTAuthenticationProvider();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/login");
    }	

}