package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(int id);
    Employee save(Employee employee);
    boolean deleteById(int id);
    Employee findByEmail(String email);
    Employee findByPhoneNumber(Long phoneNumber);
}
