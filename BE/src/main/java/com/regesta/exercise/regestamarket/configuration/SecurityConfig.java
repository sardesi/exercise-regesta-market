package com.regesta.exercise.regestamarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.regesta.exercise.regestamarket.security.JWTAuthenticationProvider;
import com.regesta.exercise.regestamarket.security.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http = http.cors().and().csrf().disable();
		http = http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http = http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login/doLogin").permitAll());
		http = http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/fe/**").permitAll());
		http = http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**").permitAll());
		
        return http.build();
        
    }
	
	@Bean
	public JWTAuthenticationProvider provider() {
	    return new JWTAuthenticationProvider();
	}

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(provider());
        return authenticationManagerBuilder.build();
    }
	 
    @Bean
	public JWTFilter jwtFilter() throws Exception {
		return new JWTFilter();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/login");
    }	

}