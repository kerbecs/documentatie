package com.app.demo.service;

import com.app.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findEmployeeId(int id);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(int id);
}
