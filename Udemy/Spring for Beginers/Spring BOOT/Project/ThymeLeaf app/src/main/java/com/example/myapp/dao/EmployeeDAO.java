package com.example.myapp.dao;

import com.example.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
 List<Employee> findAllByOrderByLastNameAsc();
}
