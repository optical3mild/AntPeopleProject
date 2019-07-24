package com.ezen.antpeople.controller.user;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ezen.antpeople.config.ExampleHandlerArgumentResolver;

@Configuration
@EnableWebMvc //xml의 <annotation-driven>
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages="com.ezen.antpeople.controller.user") // xml의 context component-scan
public class ServletConfigurationUser extends WebMvcConfigurerAdapter{
    
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ExampleHandlerArgumentResolver());
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/setfiles/**").addResourceLocations("/WEB-INF/views/setfiles/");
    }
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/user/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
}
