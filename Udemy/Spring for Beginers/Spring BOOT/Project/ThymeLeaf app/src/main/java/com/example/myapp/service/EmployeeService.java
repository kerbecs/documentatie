package com.example.myapp.service;

import com.example.myapp.dao.EmployeeDAO;
import com.example.myapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}
