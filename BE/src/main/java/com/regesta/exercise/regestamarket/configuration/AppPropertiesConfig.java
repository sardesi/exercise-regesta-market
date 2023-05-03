package com.regesta.exercise.regestamarket.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties
@PropertySource("classpath:properties/application_config.properties")
@Getter
@Setter
public class AppPropertiesConfig {
	
	private String applicationName;
	private String environment;
	private String version;

}
