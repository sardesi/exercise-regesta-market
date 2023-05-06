package com.regesta.exercise.regestamarket.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	AppPropertiesConfig applicationProperties;
	
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/fe/").setViewName("redirect:/fe/index.html");
	    registry.addViewController("/fe/login").setViewName("redirect:/fe/index.html");
	    registry.addViewController("/fe/product").setViewName("redirect:/fe/index.html");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/resources-" + applicationProperties.getVersion() + "/**").addResourceLocations("classpath:/versioned_resources/");
		registry.addResourceHandler("/resources-versioned/**").addResourceLocations("classpath:/versioned_resources/");
		registry.addResourceHandler("/resources_static/**").addResourceLocations("classpath:/static_resources/");
		registry.addResourceHandler("/fe/**").addResourceLocations("classpath:/public/");
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();       
    }

}