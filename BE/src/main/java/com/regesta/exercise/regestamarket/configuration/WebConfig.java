package com.regesta.exercise.regestamarket.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/fe/**").addResourceLocations("classpath:/public/");
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//	    registry.addMapping("/**")
//		    .allowedOrigins("*")
//		    .allowedHeaders("*")
//		    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
//		    .maxAge(-1)   // add maxAge
//		    .allowCredentials(true);
//	}
	
//	@Autowired
//	AppPropertiesConfig applicationProperties;
//    
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
//	
//	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/fe/").setViewName("redirect:/fe/index.html");
//	}
//	
//		
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();       
//    }

}