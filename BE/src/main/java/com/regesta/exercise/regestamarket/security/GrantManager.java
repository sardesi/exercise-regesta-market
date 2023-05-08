package com.regesta.exercise.regestamarket.security;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;

import com.regesta.exercise.regestamarket.utils.FileUtils;

import jakarta.servlet.ServletRequest;

public class GrantManager {
	
	protected static Properties securityProperties;
	
	protected static final Logger logger = LoggerFactory.getLogger(GrantManager.class);
	
	static {
    	try {
    		securityProperties = FileUtils.loadPropertiesFile("properties/security.properties");
    	}
    	catch(Exception ex) {
    		logger.error("Error while loading 'security.properties'", ex);
    	}
	}
	
	public void checkGrant(Authentication auth, ServletRequest request, String requestBody) throws AuthenticationServiceException {
		//Override se si desidera fare ulteriori verifiche sulla chiamata
	}
}
