package com.regesta.exercise.regestamarket.security;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.regesta.exercise.regestamarket.model.CustomLangException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter {

	private static String RESPONSE_LANG_CODE_HEADER = "regesta_responselangcode";
    
    private AuthenticationEntryPoint entryPoint;
    
    @Autowired(required=true)
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;
    
    private final RequestMatcher uriMatcher = new AntPathRequestMatcher("/api/**");
   
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	        
        try {
        	
            String stringToken = request.getHeader("api_key");

            if (stringToken == null) {
                throw new InsufficientAuthenticationException("Authorization header not found");
            }
            
            String authorizationSchema = "";
            if (stringToken.indexOf(authorizationSchema) == -1) {
                throw new InsufficientAuthenticationException("Authorization schema not found");
            }
            stringToken = stringToken.substring(authorizationSchema.length()).trim();
            try {
            	
                JWT jwt = JWTParser.parse(stringToken);
                JWTToken token = new JWTToken(jwt);
            
                Authentication auth = authenticationManager.authenticate(token);
                
            	SecurityContextHolder.getContext().setAuthentication(auth);
            	chain.doFilter(request, response);
                
            } catch (ParseException e) {
                throw new AuthenticationServiceException("Invalid token");
            } catch (CustomLangException e) {
            	response.addHeader(RESPONSE_LANG_CODE_HEADER, e.getCode());
            	response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, RESPONSE_LANG_CODE_HEADER);
            	throw new AuthenticationServiceException(e.getMessage());
            }
            
        } catch (AuthenticationException e) {
        	
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            SecurityContextHolder.clearContext();
            if (entryPoint != null) {
                entryPoint.commence((HttpServletRequest)request, response, e);
            }
            
        }
        
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        RequestMatcher matcher = new NegatedRequestMatcher(uriMatcher);
        return matcher.matches(request);
    }
    
}
