package com.test.web.cfg;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan(basePackages= {"com.test.web"})
@Import({
	 ServletConfig.class, MybatisConfig.class
})
public class RootConfig {
	@Bean
	public DataSource dataSource() {
	  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		    dataSource.setUrl("jdbc:mariadb://172.168.0.89/hanrabong");
		    dataSource.setUsername("hanrabong");
		    dataSource.setPassword("hanrabong");
		return dataSource;
	}	
	
	@Bean 
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
		//
	}
	
	
}