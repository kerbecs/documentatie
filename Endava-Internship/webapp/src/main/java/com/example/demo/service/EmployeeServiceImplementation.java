package com.example.demo.service;

import com.example.demo.custom_exception.NotFoundedDataException;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Override
    public List<Employee> getAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee getById(int id) {
        return employeeDAO.findById(id).orElseThrow(() -> new NotFoundedDataException("Employee with id " + id + " doesn't exist"));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }


    @Override
    public boolean deleteById(int id) {
        getById(id);
        employeeDAO.deleteById(id);

        return true;
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDAO.getByEmail(email);
    }

    @Override
    public Employee findByPhoneNumber(Long phoneNumber) {
        return employeeDAO.getByPhoneNumber(phoneNumber);
    }


}
