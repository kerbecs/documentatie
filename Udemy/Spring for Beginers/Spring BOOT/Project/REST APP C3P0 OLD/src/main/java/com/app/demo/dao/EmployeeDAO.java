package com.app.demo.dao;

import com.app.demo.entity.Employee;

import java.util.List;


public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findEmployeeId(int id);

    void saveEmployee(Employee employee);

    void deleteEmployee(int id);

}
