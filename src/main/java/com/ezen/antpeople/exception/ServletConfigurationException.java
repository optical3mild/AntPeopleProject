package com.ezen.antpeople.exception;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc //xml의 <annotation-driven>
@ComponentScan(basePackages="com.ezen.antpeople.exception") // xml의 context component-scan
public class ServletConfigurationException extends WebMvcConfigurerAdapter{
    
	@Bean
	public SimpleMappingExceptionResolver getExceptionResolver() {
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		
		// 지정되지 않은 예외에 대한 기본 에러페이지 입니다.
		smer.setDefaultErrorView("common/error/404");
		// 상태코드 맵핑이 없는 예외를 위한 기본 상태값 입니다.
		smer.setDefaultStatusCode(200);
		// 기본값이 "exception" 입니다. 예외 모돌 속성의 키값입니다. ${exception.message}
		smer.setExceptionAttribute("exception");
		
		return smer;
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/setfiles/**").addResourceLocations("/WEB-INF/views/setfiles/");
    }
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        return resolver;
    }
    
}
