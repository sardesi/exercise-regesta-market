package com.regesta.exercise.regestamarket.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;

/**
 * The model of the JWT Token used to manage the authentication process.
 * @author ars
 *
 */
public class JWTToken implements Authentication {

    private static final long serialVersionUID = 1L;

    private JWT jwt;
    private Collection<GrantedAuthority> authorities;
    private JWTClaimsSet  claims;
    private boolean authenticated;
    
    public JWTToken(JWT jwt) {
        this.jwt = jwt;
        this.authorities = new ArrayList<GrantedAuthority>();
        
        authenticated = false;
    }
    
    public JWT getJwt() {
        return jwt;
    }

    public JWTClaimsSet  getClaims() {
        return claims;
    }
    
    public void setClaims(JWTClaimsSet  claims) {
        this.claims = claims;
    }

    public Object getCredentials() {
        return "";
    }
    
    public Object getPrincipal() {
        return claims.getSubject();
    }

    public String getName() {
        return claims.getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public Object getDetails() {
        return claims.toJSONObject();
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }
    
}