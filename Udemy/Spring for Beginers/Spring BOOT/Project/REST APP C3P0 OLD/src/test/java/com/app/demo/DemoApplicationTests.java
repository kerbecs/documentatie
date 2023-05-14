package com.app.demo;

import com.app.demo.dao.EmployeeDAO;
import com.app.demo.entity.Employee;
import com.app.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application.properties")
class DemoApplicationTests {

	@Autowired
	EmployeeService service;
	@Autowired
	EmployeeDAO employeeDAO;


	@BeforeEach
	void beforeEach(){
		Employee employee = new Employee();
		employee.setId(10);
		employee.setFirstName("Mititiuc");
		employee.setLastName("Eduard");
		employee.setEmail("test@mail.ru");
		service.saveEmployee(employee);
	}
	@Test
	void contextLoads() {
		Employee employee = employeeDAO.findEmployeeId(10);

		assertEquals(10,employee.getId());
	}



}
