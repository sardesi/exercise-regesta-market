package com.regesta.exercise.regestamarket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


//@SpringBootApplication
//@PropertySource("classpath://application.properties")
public class RegestaMarketWebApplication extends SpringBootServletInitializer  {
	
	private static final String ERROR_PATH = "/error";
	
	public RegestaMarketWebApplication() { }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RegestaMarketWebApplication.class);
    }
}