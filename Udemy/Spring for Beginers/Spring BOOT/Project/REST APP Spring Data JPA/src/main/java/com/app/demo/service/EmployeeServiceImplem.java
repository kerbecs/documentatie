package com.app.demo.service;

import com.app.demo.dao.EmployeeRepository;
import com.app.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplem implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeId(int id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent())
            employee = optional.get();
        else
            throw new RuntimeException("Can't find an employee with id "+id);

        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);

         return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
