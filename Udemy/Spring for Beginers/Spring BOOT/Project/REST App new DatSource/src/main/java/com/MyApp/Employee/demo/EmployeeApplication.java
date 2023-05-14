package com.MyApp.Employee.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

@SpringBootApplication
@PropertySource("classpath:DB.properties")
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource(){
		return DataSourceBuilder.create().username(environment.getProperty("spring.datasource.username"))
				.password(environment.getProperty("spring.datasource.password")).url(environment.getProperty
								("spring.datasource.url"))
				.driverClassName(environment.getProperty("spring.datasource.driver")).build();
	}

	@Bean
	@Primary
	public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException, SQLException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setUser(environment.getProperty("spring.datasource.username"));
		comboPooledDataSource.setPassword(environment.getProperty("spring.datasource.password"));
		comboPooledDataSource.setJdbcUrl(environment.getProperty
				("spring.datasource.url"));
		comboPooledDataSource.setDriverClass(environment.getProperty("spring.datasource.driver"));

		comboPooledDataSource.setInitialPoolSize(Integer.parseInt(environment.getProperty("spring.jpa.properties.hibernate.c3p0.initial_size")));
		comboPooledDataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("spring.jpa.properties.hibernate.c3p0.min_size")));
		comboPooledDataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("spring.jpa.properties.hibernate.c3p0.max_size")));
		comboPooledDataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("spring.jpa.properties.hibernate.c3p0.idle_test_period")));
		comboPooledDataSource.setLoginTimeout(Integer.parseInt(environment.getProperty("spring.jpa.properties.hibernate.c3p0.timeout")));

        return comboPooledDataSource;
	}

}
