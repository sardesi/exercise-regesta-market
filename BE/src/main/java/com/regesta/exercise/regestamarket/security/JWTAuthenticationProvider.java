package com.regesta.exercise.regestamarket.security;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;
import com.regesta.exercise.regestamarket.constant.LangException;
import com.regesta.exercise.regestamarket.model.CustomLangException;
import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.utils.FileUtils;

/**
 * A custom AuthtenticationProvide inject into the security configuration flow, used to check the JWT token passed to the application.
 * @author ars
 *
 */
public class JWTAuthenticationProvider implements AuthenticationProvider {
    
    private JWSVerifier verifier;
    
    private Properties securityProperties;
    
    public final static long EXPIRY_TIME = 12800000;
    public final static long EXPIRY_TIME_10_YEARS = 315360000000L;
    
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationProvider.class);
    
    public JWTAuthenticationProvider() {
    	try {
    		securityProperties = FileUtils.loadPropertiesFile("properties/security.properties");
    	}
    	catch(Exception ex) {
    		logger.error("Error while loading 'security.properties'", ex);
    	}
    }
    
    public JWSVerifier getVerifier() throws JOSEException {
    	if(verifier == null) {
    		String sign = securityProperties.getProperty("jwt.sign").toString();
    		this.verifier = new MACVerifier(sign);
    	}
    	return this.verifier;
    }
   
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	String configuredIssuer = securityProperties.getProperty("jwt.issuer").toString();
        JWTToken jwtToken = (JWTToken) authentication;
        JWT jwt = jwtToken.getJwt();
        
        SignedJWT signedJWT = null;
        if (jwt instanceof PlainJWT) {
        	signedJWT = handlePlainToken((PlainJWT) jwt);
        } 
        else if (jwt instanceof SignedJWT) {
        	signedJWT = handleSignedToken((SignedJWT) jwt);
        } 
        else if (jwt instanceof EncryptedJWT) {
        	signedJWT = handleEncryptedToken((EncryptedJWT) jwt);
        }
        
        Date referenceTime = new Date();
        
        try {
			jwtToken.setClaims(signedJWT.getJWTClaimsSet());
		}
        catch (ParseException e) {
			throw new AuthenticationServiceException("Token claims are invalid");
		}
        
        JWTClaimsSet  claims = jwtToken.getClaims();
        
        Date expirationTime = claims.getExpirationTime();
        if (expirationTime == null || expirationTime.before(referenceTime)) {
            throw new CustomLangException(LangException.TOKEN_EXPIRED);
        }
        
        Date notBeforeTime = claims.getNotBeforeTime();
        if (notBeforeTime == null || notBeforeTime.after(referenceTime)) {
            throw new AuthenticationServiceException("Not before is after sysdate");
        }
        
        String issuer = claims.getIssuer();
        if (!configuredIssuer.equals(issuer)) {
            throw new AuthenticationServiceException("Invalid issuer");
        }
        
        jwtToken.setAuthenticated(true);
        return jwtToken;
    }

    
    public boolean supports(Class<?> authentication) {
        return JWTToken.class.isAssignableFrom(authentication);
    }
    
    private SignedJWT handlePlainToken(PlainJWT jwt) {
        throw new AuthenticationServiceException("Unsecured plain tokens are not supported");
    }
   
    
    private SignedJWT handleSignedToken(SignedJWT jwt) {
        try {
            if (!jwt.verify(getVerifier())) {
                throw new InvalidSignatureException("Signature validation failed");
            }
                        
            List<String> roles;
            try {
                roles = (List<String>)jwt.getJWTClaimsSet().getClaim("roles");
            } catch (ParseException e) {
                roles = new ArrayList<String>();
            }
            List<GrantedAuthority> tmp = new ArrayList<GrantedAuthority>();
            if (roles != null) {
                for (String role : roles) {
                    tmp.add(new SimpleGrantedAuthority(role));
                }
            }
                        
            return jwt;
        } 
        catch (JOSEException e) {
            throw new InvalidSignatureException("Signature validation failed");
        }
    }
    
    private SignedJWT handleEncryptedToken(EncryptedJWT jwt) throws AuthenticationServiceException {
        
    	try {
    		String secret = securityProperties.getProperty("jwt.secret").toString();
	        JWEObject jweObject = JWEObject.parse(jwt.getParsedString());
	
	        jweObject.decrypt(new DirectDecrypter(secret.getBytes()));
	
	        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
	        
	        handleSignedToken(signedJWT);
	
	        return signedJWT;
    	}
    	catch(Exception ex) {
    		throw new AuthenticationServiceException("Error while validating enctypted token", ex);
    	}    	
    }
    
    public String generateToken(MarketUser user, boolean rememberMeExpiry) {

    	try {
    		
    		// Create HMAC signer
    		String secret = securityProperties.getProperty("jwt.secret").toString();
    		String sign = securityProperties.getProperty("jwt.sign").toString();
    		String issuer = securityProperties.getProperty("jwt.issuer").toString();
    		
    		JWSSigner signer = new MACSigner(sign);
    		
    		

    		// Prepare JWT with claims set

    		Date issueTime = new Date();
    		JWTClaimsSet.Builder claimsSet = new JWTClaimsSet.Builder();

    		claimsSet.subject(user.getMail());
    		claimsSet.issueTime(issueTime);
    		claimsSet.notBeforeTime(issueTime);
    		claimsSet.issuer(issuer);
    		claimsSet.expirationTime(new Date(issueTime.getTime() + (rememberMeExpiry ? EXPIRY_TIME_10_YEARS : EXPIRY_TIME)));

    		claimsSet.claim("user", user.toJson());

    		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet.build());

    		// Apply the HMAC
    		signedJWT.sign(signer);

    		// Create JWE object with signed JWT as payload
    		JWEObject jweObject = new JWEObject(
    		    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM)
    		        .contentType("JWT") // required to signal nested JWT
    		        .build(),
    		    new Payload(signedJWT));

    		// Perform encryption
    		jweObject.encrypt(new DirectEncrypter(secret.getBytes()));

    		// Serialise to JWE compact form
    		String hexToken = jweObject.serialize();
    		
    		return hexToken;
    		
    	} catch(JOSEException jex) {
    		logger.error("Errore nella cifratura", jex);
    	}
    	
    	return null;
    	
    }
    
}
