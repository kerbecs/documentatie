package com.MyApp.Employee.demo.service;

import com.MyApp.Employee.demo.entity.Employee;

import java.util.List;

public interface EmployeeServiceInterf {
    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);


}
