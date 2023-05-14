package com.example.myapp.service;

import com.example.myapp.dao.EmployeeDAO;
import com.example.myapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplem implements EmployeeService{
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> optional = employeeDAO.findById(id);
        if(optional.isPresent())
            return optional.get();

        return null;
    }

    @Override
    public void save(Employee employee) {
    employeeDAO.save(employee);
    }

    @Override
    public void deleteById(int id) {
     employeeDAO.deleteById(id);
    }
}
