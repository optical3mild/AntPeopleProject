package com.ezen.antpeople.config;

import java.io.IOException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@PropertySource("classpath:/property/environment.properties")
@EnableJpaRepositories(
		basePackages ="com.ezen.antpeople.repository",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "jpaTransactionManager"
)
@EnableTransactionManagement
@Order(1)
public class RootConfig implements WebMvcConfigurer{
	
	@Inject
    private Environment env;
	
	// DataSource 부분
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
    	dataSource.setUrl(env.getProperty("dataSource.url"));
    	dataSource.setUsername(env.getProperty("dataSource.username"));
    	dataSource.setPassword(env.getProperty("dataSource.password"));
    	return dataSource;
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = 
				new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource());
		lef.setJpaVendorAdapter(jpaVenderAdapter());
		lef.setPackagesToScan("com.ezen.antpeople.entity");
		lef.afterPropertiesSet();
		return lef;
	}
	
	//jap 트랜잭션 매니저
	@Bean
	public PlatformTransactionManager jpaTransactionManager() {
		JpaTransactionManager jpaTransactionManager = 
				new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}
	

	@Bean
	public JpaVendorAdapter jpaVenderAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = 
				new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}
	
	//Session Factory
	
	  @Bean 
	  public SessionFactory sessionFactory() { 
		  LocalSessionFactoryBean
		  sessionFactory = new LocalSessionFactoryBean(); 
		  try {
			  sessionFactory.setDataSource(dataSource()); 
			  Resource configLocation = new ClassPathResource("/hibernate/hibernate.cfg.xml");
			  sessionFactory.setConfigLocation(configLocation);
			  sessionFactory.afterPropertiesSet(); 
		  } catch (IOException e) {
			  e.printStackTrace(); 
		  } 
		  return sessionFactory.getObject(); 
	  }
	 
	
	
	  //Transaction Manager
	/*
	 * @Bean public HibernateTransactionManager txManager() {
	 * HibernateTransactionManager txManager = new HibernateTransactionManager();
	 * txManager.setSessionFactory(sessionFactory()); return txManager; }
	 */



}
