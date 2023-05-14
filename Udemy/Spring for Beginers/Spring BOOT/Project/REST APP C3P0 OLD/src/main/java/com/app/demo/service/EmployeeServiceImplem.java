package com.app.demo.service;

import com.app.demo.dao.EmployeeDAO;
import com.app.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImplem implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findEmployeeId(int id) {
        return employeeDAO.findEmployeeId(id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
         employeeDAO.saveEmployee(employee);

         return employee;
    }

    @Override
    @Transactional
    public String deleteEmployee(int id) {
        Employee employee = employeeDAO.findEmployeeId(id);

        if(employee == null)
            return "Can't find employee with id "+id;
        employeeDAO.deleteEmployee(id);

        return "Employee with id "+id+" was deleted";
    }
}
