package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee,Integer> {
    Employee getByEmail(String email);
    Employee getByPhoneNumber(long phoneNumber);
}
