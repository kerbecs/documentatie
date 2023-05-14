package com.app.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Driver;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableTransactionManagement
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public DataSource dataSource(){
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setMinPoolSize(5);
		dataSource.setMaxIdleTime(3000);
		dataSource.setInitialPoolSize(5);
		try {
			dataSource.setLoginTimeout(5000);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/employee_directory?useSSL=false&serverTimezone=UTC");
		dataSource.setUser("testuser");
		dataSource.setPassword("Frb2eshox!");

		return dataSource;
	}

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("com.app.demo");
		sessionFactoryBean.setDataSource(dataSource());

       return sessionFactoryBean;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();

		transactionManager.setSessionFactory(sessionFactory);

		return transactionManager;
	}


}
