package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDAO extends JpaRepository<Department,Integer> {
}
