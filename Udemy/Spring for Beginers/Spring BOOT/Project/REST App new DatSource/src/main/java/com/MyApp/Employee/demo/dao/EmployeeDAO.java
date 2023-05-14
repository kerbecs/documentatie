package com.MyApp.Employee.demo.dao;

import com.MyApp.Employee.demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findByID(int id);

    void save(Employee employee);

    void deleteById(int id);

}
