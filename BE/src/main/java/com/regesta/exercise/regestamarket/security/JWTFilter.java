package com.regesta.exercise.regestamarket.security;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.regesta.exercise.regestamarket.model.CustomLangException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends GenericFilterBean {
	
	private static String RESPONSE_LANG_CODE_HEADER = "regesta_responselangcode";
    
    private AuthenticationEntryPoint entryPoint;
    private AuthenticationManager authenticationManager;
   
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        try {
        	
            String stringToken = req.getHeader("api_key");

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
            	res.addHeader(RESPONSE_LANG_CODE_HEADER, e.getCode());
            	res.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, RESPONSE_LANG_CODE_HEADER);
            	throw new AuthenticationServiceException(e.getMessage());
            }
            
        } catch (AuthenticationException e) {
        	
            SecurityContextHolder.clearContext();
            if (entryPoint != null) {
                entryPoint.commence((HttpServletRequest)request, res, e);
            }
            
        }
        
    }
    
}
