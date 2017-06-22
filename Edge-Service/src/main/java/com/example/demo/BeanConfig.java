package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	@Bean
	public SimpleFilter simpleFilter() {
	    return new SimpleFilter();
	}
}
