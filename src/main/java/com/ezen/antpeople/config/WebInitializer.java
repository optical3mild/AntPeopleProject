package com.ezen.antpeople.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer{
    	 
        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {    
        	
        	// RootAppContext - WebApplicationContext
    		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
    		rootAppContext.setConfigLocation("com.ezen.antpeople.config");

    		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
    		servletContext.addListener(listener);
            
    		// ServeltContext_Main - WebApplicationContext
            AnnotationConfigWebApplicationContext ServletMaincontext = new AnnotationConfigWebApplicationContext();
            ServletMaincontext.setConfigLocation("com.ezen.antpeople.controller.main");
            
            ServletRegistration.Dynamic dispatcherMain = servletContext.addServlet("DispatcherServletMain", new DispatcherServlet(ServletMaincontext));
            dispatcherMain.setLoadOnStartup(1);
            dispatcherMain.addMapping("/");
            
            // ServeltContext_User - WebApplicationContext
            AnnotationConfigWebApplicationContext servletUserContext = new AnnotationConfigWebApplicationContext();
            ServletMaincontext.setConfigLocation("com.ezen.antpeople.controller.user");

    		ServletRegistration.Dynamic dispatcherUser = servletContext.addServlet("DispatcherServletUser", new DispatcherServlet(servletUserContext));
    		dispatcherUser.setLoadOnStartup(2);
    		dispatcherUser.addMapping("/login.do");
    		dispatcherUser.setAsyncSupported(true);


            
            
            // 인코딩 필터 적용
            FilterRegistration.Dynamic charaterEncodingFilter = servletContext.addFilter("charaterEncodingFilter", new CharacterEncodingFilter());
            charaterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
            charaterEncodingFilter.setInitParameter("encoding", "UTF-8");
            charaterEncodingFilter.setInitParameter("forceEncoding", "true");
        }    
    }

